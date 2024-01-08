package com.sparta.spartalecture.course.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LikeResponseDto {
    public String isLike;

    public LikeResponseDto(String isLike) {
        this.isLike = isLike;
    }
}
