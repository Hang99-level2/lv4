package com.sparta.spartalecture.user.service;

import com.sparta.spartalecture.jwt.JwtUtil;
import com.sparta.spartalecture.user.dto.LoginRequestDto;
import com.sparta.spartalecture.user.dto.SignupRequestDto;
import com.sparta.spartalecture.user.dto.SignupResponseDto;
import com.sparta.spartalecture.user.entity.User;
import com.sparta.spartalecture.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    public SignupResponseDto signup(SignupRequestDto signupRequestDto) {
        String email = signupRequestDto.getEmail();
        signupRequestDto.setPassword(passwordEncoder.encode(signupRequestDto.getPassword()));
        Optional<User> checkEmail = userRepository.findByEmail(email);

        if (checkEmail.isPresent()) {
            throw new IllegalArgumentException("중복된 이메일 입니다.");
        }
        User user = new User(signupRequestDto);
        userRepository.save(user);
        return new SignupResponseDto(user);
    }

    public String login(LoginRequestDto loginRequestDto) {
        String email = loginRequestDto.getEmail();
        String password = loginRequestDto.getPassword();

        User user = userRepository.findByEmail(email).orElseThrow(
                ()->new IllegalArgumentException("등록된 사용자가 없습니다.")
        );
        if(!passwordEncoder.matches(password,user.getPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        return jwtUtil.createToken(email,user.getRole());
    }

    public User getUserById(long id){
        return userRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당하는 유저가 없습니다."));
    }
}
