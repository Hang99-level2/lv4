package com.sparta.spartalecture.controller;

import com.sparta.spartalecture.dto.TeacherSignupRequestDto;
import com.sparta.spartalecture.dto.TeacherSignupResponseDto;
import com.sparta.spartalecture.service.TeacherService;
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
    public TeacherSignupResponseDto teacherSignups(@RequestBody TeacherSignupRequestDto teacherSignupRequestDto){
        return teacherService.teacherSignups(teacherSignupRequestDto);
    }
}
