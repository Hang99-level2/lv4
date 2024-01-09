package com.sparta.spartalecture.teacher.controller;


import com.sparta.spartalecture.teacher.dto.TeacherRequestDto;
import com.sparta.spartalecture.teacher.dto.TeacherResponseDto;
import com.sparta.spartalecture.teacher.service.TeacherService;
import com.sparta.spartalecture.user.entity.UserRoleEnum;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @PostMapping("/teachers")
    public TeacherResponseDto createTeacher(@RequestBody TeacherRequestDto teacherRequestDto){
        return teacherService.createUser(teacherRequestDto);
    }
}
