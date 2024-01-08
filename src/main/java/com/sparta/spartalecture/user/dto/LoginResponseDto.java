package com.sparta.spartalecture.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDto {
    private String res;
    public LoginResponseDto(String str) {
        this.res = str;
    }
}
