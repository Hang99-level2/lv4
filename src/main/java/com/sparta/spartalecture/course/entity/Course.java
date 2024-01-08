package com.sparta.spartalecture.course.entity;

import com.sparta.spartalecture.Teacher.entity.Teacher;
import com.sparta.spartalecture.course.dto.CourseRequestDto;
import com.sparta.spartalecture.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String lecture;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private String introduction;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String addDate;

    @OneToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @OneToMany
    @JoinColumn(name="user_id")
    private List<User> users;

    public Course(CourseRequestDto courseRequestDto,Teacher teacher) {
        this.lecture = courseRequestDto.getLecture();
        this.price = courseRequestDto.getPrice();
        this.introduction = courseRequestDto.getIntroduction();
        this.category = courseRequestDto.getCategory();
        this.addDate = courseRequestDto.getAddDate();
        this.teacher = teacher;
        this.users = new ArrayList<>();
    }
}
