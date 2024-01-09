package com.sparta.spartalecture.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseRequestDto {
    private String lecture;
    private int price;
    private String introduction;
    private String category;
    private Long teacherId;
    private String addDate;

}
