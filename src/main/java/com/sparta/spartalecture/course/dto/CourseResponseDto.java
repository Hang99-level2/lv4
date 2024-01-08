package com.sparta.spartalecture.course.dto;

import com.sparta.spartalecture.course.entity.Course;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseResponseDto {
    private String lecture;
    private int price;
    private String introduction;
    private String category;
    private long teacherId;
    private String addDate;

    public CourseResponseDto(Course course) {
        this.lecture = course.getLecture();
        this.price = course.getPrice();
        this.introduction = course.getIntroduction();
        this.category = course.getCategory();
        this.teacherId = course.getTeacher().getId();
        this.addDate = course.getAddDate();
    }
}
