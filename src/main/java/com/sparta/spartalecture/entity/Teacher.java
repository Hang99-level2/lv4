package com.sparta.spartalecture.entity;

import com.sparta.spartalecture.dto.TeacherSignupRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "teacher")
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

    public Teacher(TeacherSignupRequestDto teacherSignupRequestDto) {
        this.name = teacherSignupRequestDto.getName();
        this.age = teacherSignupRequestDto.getAge();
        this.company = teacherSignupRequestDto.getCompany();;
        this.number = teacherSignupRequestDto.getNumber();;
        this.introduction = teacherSignupRequestDto.getIntroduction();
    }
}