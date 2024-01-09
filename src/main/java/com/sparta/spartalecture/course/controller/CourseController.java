package com.sparta.spartalecture.course.controller;

import com.sparta.spartalecture.course.dto.CourseDetailResponseDto;
import com.sparta.spartalecture.course.dto.CourseRequestDto;
import com.sparta.spartalecture.course.dto.CourseResponseDto;
import com.sparta.spartalecture.course.dto.LikeResponseDto;
import com.sparta.spartalecture.course.service.CourseService;
import com.sparta.spartalecture.user.entity.UserRoleEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping("/courses")
    public CourseResponseDto createCourse(@Valid @RequestBody CourseRequestDto courseRequestDto){
        return courseService.createCourse(courseRequestDto);
    }

    @GetMapping("/courses/detail/{courseId}")
    public CourseDetailResponseDto getCourseDetailById(@PathVariable long courseId){
        return courseService.getCourseDetailById(courseId);
    }

    @GetMapping("/courses/{category}")
    public List<CourseResponseDto> getCoursesByCategory(@PathVariable String category,
                                                        @RequestParam String sortBy,
                                                        @RequestParam boolean isAsc){
        return courseService.getCoursesByCategory(category,sortBy,isAsc);
    }

    @PutMapping("/likes/{courseId}")
    public LikeResponseDto updateLike(@PathVariable long courseId,
                                      HttpServletRequest req){

        long userId = (long) req.getAttribute("userId");
        return courseService.updateLike(userId,courseId);
    }

}
