package com.sparta.spartalecture.dto;

import com.sparta.spartalecture.entity.Like;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LikeResponseDto {
    private Long likeCount;
    public LikeResponseDto(Long likeCount){
        this.likeCount = likeCount;
    }
}
