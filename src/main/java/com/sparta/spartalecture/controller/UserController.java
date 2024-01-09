package com.sparta.spartalecture.controller;

import com.sparta.spartalecture.dto.LoginRequestDto;
import com.sparta.spartalecture.dto.LoginResponseDto;
import com.sparta.spartalecture.dto.UserSignupRequestDto;
import com.sparta.spartalecture.dto.UserSignupResponse;
import com.sparta.spartalecture.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;
    @PostMapping("/users")
    public UserSignupResponse UserSingUp(@Valid @RequestBody UserSignupRequestDto signupRequestDto){
        return userService.UserSingUp(signupRequestDto);
    }
    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse res){
        String token = userService.login(loginRequestDto,res);
        if(token == null){
            return new LoginResponseDto("로그인 실패");
        }
        return new LoginResponseDto("로그인 성공");
    }
}
