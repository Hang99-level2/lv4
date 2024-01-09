package com.sparta.spartalecture.service;

import com.sparta.spartalecture.dto.LoginRequestDto;
import com.sparta.spartalecture.dto.LoginResponseDto;
import com.sparta.spartalecture.dto.UserSignupRequestDto;
import com.sparta.spartalecture.dto.UserSignupResponse;
import com.sparta.spartalecture.entity.User;
import com.sparta.spartalecture.entity.UserRoleEnum;
import com.sparta.spartalecture.jwt.JwtUtil;
import com.sparta.spartalecture.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserSignupResponse UserSingUp(UserSignupRequestDto signupRequestDto) {
        String email = signupRequestDto.getEmail();
        String password = passwordEncoder.encode(signupRequestDto.getPassword());
        Optional<User> checkEmail = userRepository.findByEmail(email);
        if (checkEmail.isPresent()){
            throw new IllegalArgumentException("중복된 email이 존재합니다");
        }
        String gender = signupRequestDto.getGender();
        String number =signupRequestDto.getNumber();
        String address = signupRequestDto.getAddress();
        String role = signupRequestDto.getRole();
        User user = new User(email,password, gender,number,address, role);
        userRepository.save(user);
        return new UserSignupResponse(user);
    }

    public String login(LoginRequestDto loginRequestDto, HttpServletResponse res) {
        String email =loginRequestDto.getEmail();
        String password = loginRequestDto.getPassword();
        User user = userRepository.findByEmail(email).orElseThrow(
                ()-> new IllegalArgumentException("등록된 email이 없습니다.")
        );
        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new IllegalArgumentException("비밀번호가 일치 하지 않습니다");
        }
        String token = JwtUtil.createToken(user.getEmail(), user.getRole());
        JwtUtil.addJwtToCookie(token,res);
        return token;
    }
}
