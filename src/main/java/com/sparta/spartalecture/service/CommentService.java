package com.sparta.spartalecture.service;

import com.sparta.spartalecture.dto.CommentRequestDto;
import com.sparta.spartalecture.dto.CommentResponseDto;
import com.sparta.spartalecture.entity.Comment;
import com.sparta.spartalecture.entity.Course;
import com.sparta.spartalecture.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final CourseService courseService;

    public CommentResponseDto createComment(CommentRequestDto commentRequestDto) {
        Course course = courseService.getCourses(commentRequestDto.getCourseId());
        Comment comment = new Comment(commentRequestDto, course);
        Comment saveComment = commentRepository.save(comment);
        return new CommentResponseDto(saveComment);
    }

    public CommentResponseDto updateComment(Long commentId ,CommentRequestDto commentRequestDto) {
        Comment comment = commentRepository.findById(commentId).orElseThrow();
        comment.update(commentRequestDto.getContext());
        return new CommentResponseDto(comment);
    }
}
