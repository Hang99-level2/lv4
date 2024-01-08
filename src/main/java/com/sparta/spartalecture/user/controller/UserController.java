package com.sparta.spartalecture.user.controller;


import com.sparta.spartalecture.user.dto.LoginRequestDto;
import com.sparta.spartalecture.user.dto.LoginResponseDto;
import com.sparta.spartalecture.user.dto.SignupRequestDto;
import com.sparta.spartalecture.user.dto.SignupResponseDto;
import com.sparta.spartalecture.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public SignupResponseDto signup(@Valid @RequestBody SignupRequestDto signupRequestDto){
        return userService.signup(signupRequestDto);
    }

    @PostMapping("/users/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse res){
        String token = userService.login(loginRequestDto,res);
        if(token == null){
            return new LoginResponseDto("로그인 실패");
        }
        else{
            return new LoginResponseDto(token);
        }
    }
}
