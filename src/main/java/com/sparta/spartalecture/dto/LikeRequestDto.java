package com.sparta.spartalecture.dto;

import com.sparta.spartalecture.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LikeRequestDto {
    private Long userId;
    private Long courseId;
}
