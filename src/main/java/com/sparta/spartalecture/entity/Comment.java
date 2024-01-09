package com.sparta.spartalecture.entity;

import com.sparta.spartalecture.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name="courseId")
    private Course course;

    @Column(nullable = false)
    private String context;

    public Comment(CommentRequestDto commentRequestDto, Course course) {
        this.course = course;
        this.context = commentRequestDto.getContext();
    }

    public void update(String context) {
        this.context = context;
    }
}
