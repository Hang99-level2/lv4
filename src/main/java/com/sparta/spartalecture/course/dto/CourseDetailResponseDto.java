package com.sparta.spartalecture.course.dto;

import com.sparta.spartalecture.course.entity.Course;
import com.sparta.spartalecture.teacher.dto.TeacherResponseDto;
import com.sparta.spartalecture.user.dto.CommentRequestDto;
import com.sparta.spartalecture.user.dto.CommentResponseDto;
import com.sparta.spartalecture.user.entity.Comment;
import com.sparta.spartalecture.user.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CourseDetailResponseDto {

    private String lecture;
    private int price;
    private String introduction;
    private String category;
    private String addDate;
    private TeacherResponseDto teacherResponseDto;
    private long likes;
    private List<CommentResponseDto> comments;

    public CourseDetailResponseDto(Course course) {
        this.lecture = course.getLecture();
        this.price = course.getPrice();
        this.introduction = course.getIntroduction();
        this.category = course.getCategory();
        this.addDate = course.getAddDate();
        this.likes = course.getUsers().size();
        this.comments = course.getComments().stream().map(CommentResponseDto::new).toList();
        this.teacherResponseDto = new TeacherResponseDto(course.getTeacher());
    }
}
