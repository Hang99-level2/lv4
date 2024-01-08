package com.sparta.spartalecture.course.controller;

import com.sparta.spartalecture.course.dto.CourseDetailResponseDto;
import com.sparta.spartalecture.course.dto.CourseRequestDto;
import com.sparta.spartalecture.course.dto.CourseResponseDto;
import com.sparta.spartalecture.course.dto.LikeResponseDto;
import com.sparta.spartalecture.course.entity.Course;
import com.sparta.spartalecture.course.service.CourseService;
import com.sparta.spartalecture.user.entity.UserRoleEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/courses")
    public CourseResponseDto createCourse(@Valid @RequestBody CourseRequestDto courseRequestDto,
                                          HttpServletRequest req){
        UserRoleEnum adminRole = (UserRoleEnum) req.getAttribute("role");
        if(!adminRole.equals(UserRoleEnum.ADMIN)){
            throw new IllegalArgumentException("권한 없음");
        }
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
        if(!sortBy.equals("lecture")&&!sortBy.equals("price")&&!sortBy.equals("addDate")){
            throw new IllegalArgumentException("잘못된 접근입니다.");
        }

        return courseService.getCoursesByCategory(category,sortBy,isAsc);
    }

    @PutMapping("/likes/{courseId}")
    public LikeResponseDto updateLike(@PathVariable long courseId,
                                      HttpServletRequest req){

        Object userIdAttribute = req.getAttribute("userId");
        System.out.println("userIdAttribute = " + userIdAttribute);
        if (userIdAttribute == null) {
            throw new IllegalArgumentException("유저 아이디가 존재하지 않습니다.");
        }
        long userId = (long) userIdAttribute;

        return courseService.updateLike(userId,courseId);
    }

}
