package com.sparta.spartalecture.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name ="likes")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name ="UserId")
    private User user;
    @OneToOne
    @JoinColumn(name ="CourseId")
    private Course course;
    @Column(name ="likeCount")
    private Long likeCount;

    public Like(Course course, User user) {
        this.user = user;
        this.course = course;
    }

    public void setLikeCount(Long i) {
        this.likeCount = i;
    }
}
