package com.sparta.spartalecture.entity;

import com.sparta.spartalecture.dto.CourseRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Stack;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String lecture;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private String introduction;

    @Column(nullable = false)
    private String category;

    @OneToOne
    @JoinColumn(name ="teacherId")
    private Teacher teacher;

    @Column(nullable = false)
    private String addDate;

    public Course(CourseRequestDto courseRequestDto, Teacher teacher) {
        this.lecture = courseRequestDto.getLecture();
        this.price = courseRequestDto.getPrice();
        this.introduction = courseRequestDto.getIntroduction();
        this.category = courseRequestDto.getCategory();;
        this.teacher= teacher;
        this.addDate = courseRequestDto.getAddDate();
    }

}
