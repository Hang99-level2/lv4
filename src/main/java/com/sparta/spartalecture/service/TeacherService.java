package com.sparta.spartalecture.service;

import com.sparta.spartalecture.dto.TeacherSignupRequestDto;
import com.sparta.spartalecture.dto.TeacherSignupResponseDto;
import com.sparta.spartalecture.entity.Teacher;
import com.sparta.spartalecture.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;

    public TeacherSignupResponseDto teacherSignups(TeacherSignupRequestDto teacherSignupRequestDto) {
        Teacher teacher = new Teacher(teacherSignupRequestDto);
        teacherRepository.save(teacher);
        return new TeacherSignupResponseDto(teacher);
    }
}
