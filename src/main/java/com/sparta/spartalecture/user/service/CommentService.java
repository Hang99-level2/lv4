package com.sparta.spartalecture.user.service;

import com.sparta.spartalecture.course.entity.Course;
import com.sparta.spartalecture.course.service.CourseService;
import com.sparta.spartalecture.user.dto.CommentRequestDto;
import com.sparta.spartalecture.user.dto.CommentResponseDto;
import com.sparta.spartalecture.user.entity.Comment;
import com.sparta.spartalecture.user.entity.User;
import com.sparta.spartalecture.user.repository.CommentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final CourseService courseService;

    public Comment getCommentById(long id){
        return commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디에 해당하는 코멘트가 없습니다."));
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
            throw new AccessDeniedException("다른 작성자의 댓글 입니다.");
        }

        comment.update(commentRequestDto.getContext());
        return new CommentResponseDto(comment.getUser().getId(),comment.getContent());
    }

    @Transactional
    public long deleteComment(long userId, long commentId) {
        Comment comment = getCommentById(commentId);
        if (userId != comment.getUser().getId()){
            throw new AccessDeniedException("다른 작성자의 댓글 입니다.");
        }
        commentRepository.delete(comment);
        return commentId;
    }
}
