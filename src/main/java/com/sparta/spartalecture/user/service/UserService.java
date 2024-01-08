package com.sparta.spartalecture.user.service;

import com.sparta.spartalecture.jwt.JwtUtil;
import com.sparta.spartalecture.user.dto.LoginRequestDto;
import com.sparta.spartalecture.user.dto.LoginResponseDto;
import com.sparta.spartalecture.user.dto.SignupRequestDto;
import com.sparta.spartalecture.user.dto.SignupResponseDto;
import com.sparta.spartalecture.user.entity.User;
import com.sparta.spartalecture.user.entity.UserRoleEnum;
import com.sparta.spartalecture.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public SignupResponseDto signup(SignupRequestDto signupRequestDto) {
        String email = signupRequestDto.getEmail();
        signupRequestDto.setPassword(passwordEncoder.encode(signupRequestDto.getPassword()));
        //중복 이메일 체크
        Optional<User> checkEmail = userRepository.findByEmail(email);
        if (checkEmail.isPresent()) {
            throw new IllegalArgumentException("이메일 중복");
        }

        User user = new User(signupRequestDto);
        userRepository.save(user);
        return new SignupResponseDto(user);
    }


    public String login(LoginRequestDto loginRequestDto, HttpServletResponse res) {
        String email = loginRequestDto.getEmail();
        String password = loginRequestDto.getPassword();

        User user = userRepository.findByEmail(email).orElseThrow(
                ()->new IllegalArgumentException("등록된 사용자가 없습니다.")
        );

        if(!passwordEncoder.matches(password,user.getPassword())){
            throw new IllegalArgumentException("비밀번호가일치하지 않습니다.");
        }

        String token = jwtUtil.createToken(email,user.getRole());
        jwtUtil.addJwtToCookie(token,res);
        return token;
    }
}

