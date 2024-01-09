package com.sparta.spartalecture.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherSignupRequestDto {
    private String name;
    private String age;
    private String company;
    private String number;
    private String introduction;
}
