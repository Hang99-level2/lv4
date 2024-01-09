package com.sparta.spartalecture.dto;

import com.sparta.spartalecture.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignupResponse {
    private String email;

    public UserSignupResponse(User user) {
        this.email = user.getEmail();
    }
}
