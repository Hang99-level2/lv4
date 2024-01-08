package com.sparta.spartalecture.course.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseRequestDto {
    private String lecture;
    private int price;
    private String introduction;
    @Pattern(regexp = "^(Spring|Node|React)$", message = "CATEGORY => 'Spring' or 'Node' or 'React'")
    private String category;
    private int teacherId;
    private String addDate;

}
