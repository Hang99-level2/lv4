package com.sparta.spartalecture.controller;

import com.sparta.spartalecture.dto.CommentRequestDto;
import com.sparta.spartalecture.dto.CommentResponseDto;
import com.sparta.spartalecture.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comment")
    public CommentResponseDto createComment(@RequestBody CommentRequestDto commentRequestDto){
        return commentService.createComment(commentRequestDto);
    }
    @PutMapping("/comment/{commentId}")
    public CommentResponseDto updateComment(@PathVariable Long commentId ,@RequestBody CommentRequestDto commentRequestDto){
        return commentService.updateComment(commentId,commentRequestDto);
    }
}
