package com.sparta.spartalecture.controller;

import com.sparta.spartalecture.dto.TeacherSignupRequestDto;
import com.sparta.spartalecture.dto.TeacherSignupResponseDto;
import com.sparta.spartalecture.entity.Teacher;
import com.sparta.spartalecture.entity.UserRoleEnum;
import com.sparta.spartalecture.service.TeacherService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TeacherController {
    private final TeacherService teacherService;

    @PostMapping("/teachers")
    public TeacherSignupResponseDto teacherSignups(@RequestBody TeacherSignupRequestDto teacherSignupRequestDto, HttpServletRequest req){
        if (req.getAttribute("role").equals(UserRoleEnum.USER)){
            return new TeacherSignupResponseDto(new Teacher());
        }
        return teacherService.teacherSignups(teacherSignupRequestDto);
    }
}
