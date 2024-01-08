package com.sparta.spartalecture.teacher.dto;


import com.sparta.spartalecture.teacher.entity.Teacher;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherResponseDto {
    private String name;
    private String age;
    private String company;
    private String introduction;

    public TeacherResponseDto(Teacher teacher){
        this.name = teacher.getName();
        this.age = teacher.getAge();
        this.company = teacher.getCompany();
        this.introduction = teacher.getIntroduction();
    }
}
