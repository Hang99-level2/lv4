package com.sparta.spartalecture.Teacher.service;

import com.sparta.spartalecture.Teacher.dto.TeacherRequestDto;
import com.sparta.spartalecture.Teacher.dto.TeacherResponseDto;
import com.sparta.spartalecture.Teacher.entity.Teacher;
import com.sparta.spartalecture.Teacher.repository.TeacherRepository;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public TeacherResponseDto createUser(TeacherRequestDto teacherRequestDto) {
        Teacher teacher = new Teacher(teacherRequestDto);
        teacherRepository.save(teacher);
        return new TeacherResponseDto(teacher);
    }

    public Teacher getTeacherById(long id){
        return teacherRepository.findById(id).orElseThrow();
    }
}
