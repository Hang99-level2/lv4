package com.sparta.spartalecture.Teacher.controller;

import com.sparta.spartalecture.Teacher.dto.TeacherRequestDto;
import com.sparta.spartalecture.Teacher.dto.TeacherResponseDto;
import com.sparta.spartalecture.Teacher.service.TeacherService;
import com.sparta.spartalecture.user.entity.UserRoleEnum;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping("/teachers")
    public TeacherResponseDto createTeacher(@RequestBody TeacherRequestDto teacherRequestDto,
                                            HttpServletRequest req){

        UserRoleEnum adminRole = (UserRoleEnum) req.getAttribute("role");
        if(!adminRole.equals(UserRoleEnum.ADMIN)){
            throw new IllegalArgumentException("권한이 없습니다");
        }
        return teacherService.createUser(teacherRequestDto);
    }




}
