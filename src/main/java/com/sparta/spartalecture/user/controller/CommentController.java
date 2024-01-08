package com.sparta.spartalecture.user.controller;

import com.sparta.spartalecture.user.dto.CommentRequestDto;
import com.sparta.spartalecture.user.dto.CommentResponseDto;
import com.sparta.spartalecture.user.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comments/{courseId}")
    public CommentResponseDto createComment(@PathVariable long courseId,
                                            @RequestBody CommentRequestDto commentRequestDto,
                                            HttpServletRequest req){
        Object userIdAttribute = req.getAttribute("userId");
        System.out.println("userIdAttribute = " + userIdAttribute);
        if (userIdAttribute == null) {
            throw new IllegalArgumentException("유저 아이디가 존재하지 않습니다.");
        }
        long userId = (long) userIdAttribute;
        return commentService.createComment(userId,courseId,commentRequestDto);
    }

    @PutMapping("/comments/{commentId}")
    public CommentResponseDto updateComment(@PathVariable long commentId,
                                            @RequestBody CommentRequestDto commentRequestDto,
                                            HttpServletRequest req){
        Object userIdAttribute = req.getAttribute("userId");
        System.out.println("userIdAttribute = " + userIdAttribute);
        if (userIdAttribute == null) {
            throw new IllegalArgumentException("유저 아이디가 존재하지 않습니다.");
        }
        long userId = (long) userIdAttribute;
        return commentService.updateComment(userId,commentId,commentRequestDto);
    }

    @DeleteMapping("/comments/{commentId}")
    public long DeleteComment(@PathVariable long commentId,
                              HttpServletRequest req){
        Object userIdAttribute = req.getAttribute("userId");
        System.out.println("userIdAttribute = " + userIdAttribute);
        if (userIdAttribute == null) {
            throw new IllegalArgumentException("유저 아이디가 존재하지 않습니다.");
        }
        long userId = (long) userIdAttribute;
        return commentService.deleteComment(userId,commentId);
    }

}
