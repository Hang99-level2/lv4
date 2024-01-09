package com.sparta.spartalecture.controller;

import com.sparta.spartalecture.dto.CourseRequestDto;
import com.sparta.spartalecture.dto.CourseResponseDto;
import com.sparta.spartalecture.dto.LikeRequestDto;
import com.sparta.spartalecture.dto.LikeResponseDto;
import com.sparta.spartalecture.entity.Course;
import com.sparta.spartalecture.repository.CourseRepository;
import com.sparta.spartalecture.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CourseController {
    private final CourseService courseService;
    @PostMapping("/courses")
    public CourseResponseDto courseCreate(@RequestBody CourseRequestDto courseRequestDto){
        return courseService.courseCreate(courseRequestDto);
    }

    @GetMapping("/courses/detail/{courseId}")
    public CourseResponseDto getCourses(@PathVariable Long courseId){
        return new CourseResponseDto(courseService.getCourses(courseId));
    }
    @GetMapping("/courses/{category}")
    public CourseResponseDto getCategory(@PathVariable String category){
        return new CourseResponseDto(courseService.getCategory(category));
    }
    @PutMapping("/like")
    public LikeResponseDto updateLike(@RequestBody LikeRequestDto likeRequestDto){
        return courseService.updateLike(likeRequestDto);
    }
}
