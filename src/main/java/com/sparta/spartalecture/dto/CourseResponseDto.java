package com.sparta.spartalecture.dto;

import com.sparta.spartalecture.entity.Course;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseResponseDto {
    private String lecture;
    private int price;
    private String introduction;
    private String category;
    private Long teacherId;
    private String addDate;

    public CourseResponseDto(Course course) {
        this.lecture = course.getLecture();
        this.price = course.getPrice();
        this.introduction =course.getIntroduction();
        this.category = course.getCategory();
        this.teacherId = course.getId();
        this.addDate =course.getAddDate();
    }
}
