package com.sparta.spartalecture.Teacher.entity;

import com.sparta.spartalecture.Teacher.dto.TeacherRequestDto;
import com.sparta.spartalecture.course.dto.CourseRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String age;

    @Column(nullable = false)
    private String company;

    @Column(nullable = false)
    private String number;

    @Column(nullable = false)
    private String introduction;

    public Teacher(TeacherRequestDto teacherRequestDto) {
        this.name = teacherRequestDto.getName();
        this.age = teacherRequestDto.getAge();
        this.company = teacherRequestDto.getCompany();
        this.number = teacherRequestDto.getNumber();
        this.introduction = teacherRequestDto.getIntroduction();
    }

}

