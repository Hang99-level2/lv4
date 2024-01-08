package com.sparta.spartalecture.course.controller;

import com.sparta.spartalecture.course.dto.CourseRequestDto;
import com.sparta.spartalecture.course.dto.CreateCourseResponseDto;
import com.sparta.spartalecture.course.entity.Course;
import com.sparta.spartalecture.course.service.CourseService;
import com.sparta.spartalecture.user.entity.UserRoleEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/courses")
    public CreateCourseResponseDto createCourse(@Valid @RequestBody CourseRequestDto courseRequestDto,
                                                HttpServletRequest req){
        UserRoleEnum adminRole = (UserRoleEnum) req.getAttribute("role");
        if(!adminRole.equals(UserRoleEnum.ADMIN)){
            throw new IllegalArgumentException("권한 없음");
        }

        return courseService.createCourse(courseRequestDto);

    }
}
