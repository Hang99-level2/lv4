package com.sparta.spartalecture.repository;

import com.sparta.spartalecture.dto.CommentRequestDto;
import com.sparta.spartalecture.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}