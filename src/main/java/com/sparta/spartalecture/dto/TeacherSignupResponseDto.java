package com.sparta.spartalecture.dto;

import com.sparta.spartalecture.entity.Teacher;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherSignupResponseDto {
    private String name;
    private String age;
    private String company;
    private String number;
    private String introduction;

    public TeacherSignupResponseDto(Teacher teacher) {
        this.name = teacher.getName();;
        this.age = teacher.getAge();
        this.company = teacher.getCompany();
        this.number = teacher.getNumber();
        this.introduction = teacher.getIntroduction();;
    }
}
