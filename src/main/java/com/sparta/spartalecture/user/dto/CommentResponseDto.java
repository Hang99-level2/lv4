package com.sparta.spartalecture.user.dto;

import com.sparta.spartalecture.course.entity.Course;
import com.sparta.spartalecture.user.entity.Comment;
import com.sparta.spartalecture.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentResponseDto {
    private long userId;
    private long courseId;
    private String comment;

    public CommentResponseDto(long userId, long courseId, String comment){
        this.userId = userId;
        this.courseId = courseId;
        this.comment = comment;
    }

    public CommentResponseDto(Comment comment) {
        this.userId = comment.getUser().getId();
        this.courseId = comment.getCourse().getId();
        this.comment = comment.getContent();
    }
}
