package com.sparta.spartalecture.teacher.service;


import com.sparta.spartalecture.teacher.dto.TeacherRequestDto;
import com.sparta.spartalecture.teacher.dto.TeacherResponseDto;
import com.sparta.spartalecture.teacher.entity.Teacher;
import com.sparta.spartalecture.teacher.repository.TeacherRepository;
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
