package com.sparta.spartalecture.dto;

import com.sparta.spartalecture.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentResponseDto {
    private Long courseId;
    private String context;

    public CommentResponseDto(Comment saveComment) {
        this.courseId =saveComment.getId();
        this.context = saveComment.getContext();
    }
}
