package com.sparta.spartalecture.teacher.service;


import com.sparta.spartalecture.teacher.dto.TeacherRequestDto;
import com.sparta.spartalecture.teacher.dto.TeacherResponseDto;
import com.sparta.spartalecture.teacher.entity.Teacher;
import com.sparta.spartalecture.teacher.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherResponseDto createUser(TeacherRequestDto teacherRequestDto) {
        Teacher teacher = new Teacher(teacherRequestDto);
        teacherRepository.save(teacher);
        return new TeacherResponseDto(teacher);
    }

    public Teacher getTeacherById(long id){
        return teacherRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("아이디에 해당하는 강사가 존재하지 않습니다."));
    }
}
