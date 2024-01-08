package com.sparta.spartalecture.user.service;

import com.sparta.spartalecture.course.entity.Course;
import com.sparta.spartalecture.course.service.CourseService;
import com.sparta.spartalecture.user.dto.CommentRequestDto;
import com.sparta.spartalecture.user.dto.CommentResponseDto;
import com.sparta.spartalecture.user.entity.Comment;
import com.sparta.spartalecture.user.entity.User;
import com.sparta.spartalecture.user.repository.CommentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final CourseService courseService;

    public CommentService(CommentRepository commentRepository, UserService userService, CourseService courseService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.courseService = courseService;
    }
    public Comment getCommentById(long id){
        return commentRepository.findById(id).orElseThrow();
    }
    @Transactional
    public CommentResponseDto createComment(long userId, long courseId, CommentRequestDto commentRequestDto) {
        User user = userService.getUserById(userId);
        Course course = courseService.getCourseById(courseId);
        Comment comment = new Comment(user,commentRequestDto.getContext());
        course.getComments().add(comment);
        commentRepository.save(comment);
        return new CommentResponseDto(user.getId(),comment.getContent());
    }

    @Transactional
    public CommentResponseDto updateComment(long userId, long commentId, CommentRequestDto commentRequestDto) {
        Comment comment = getCommentById(commentId);
        if (userId != comment.getUser().getId()){
            throw new IllegalArgumentException("작성자가 다릅니다.");
        }

        comment.update(commentRequestDto.getContext());
        return new CommentResponseDto(comment.getUser().getId(),comment.getContent());
    }

    @Transactional
    public long deleteComment(long userId, long commentId) {
        Comment comment = getCommentById(commentId);
        if (userId != comment.getUser().getId()){
            throw new IllegalArgumentException("작성자가 다릅니다.");
        }
        commentRepository.delete(comment);
        return commentId;
    }
}
