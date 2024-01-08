package com.sparta.spartalecture.user.entity;

import com.sparta.spartalecture.course.entity.Course;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String content;

    public Comment(User user,String content) {
        this.user = user;
        this.content = content;
    }

    public void update(String content) {
        this.content = content;
    }
}