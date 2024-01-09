package com.sparta.spartalecture.service;

import com.sparta.spartalecture.dto.CourseRequestDto;
import com.sparta.spartalecture.dto.CourseResponseDto;
import com.sparta.spartalecture.entity.Course;
import com.sparta.spartalecture.entity.Teacher;
import com.sparta.spartalecture.repository.CourseRepository;
import com.sparta.spartalecture.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;

    public CourseResponseDto courseCreate(CourseRequestDto courseRequestDto) {
        Teacher teacher = teacherRepository.getReferenceById(courseRequestDto.getTeacherId());
        Course course = new Course(courseRequestDto, teacher);
        courseRepository.save(course);
        return new CourseResponseDto(course);
    }

    public Course getCourses(Long courseId) {
        return courseRepository.findById(courseId).orElseThrow(()-> new IllegalArgumentException("아이디 정보가 없습니다."));
    }

    public Course getCategory(String category) {
        return courseRepository.findByCategory(category).orElseThrow();
    }
}
