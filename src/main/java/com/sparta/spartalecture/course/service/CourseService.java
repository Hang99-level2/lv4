package com.sparta.spartalecture.course.service;

import com.sparta.spartalecture.Teacher.dto.TeacherResponseDto;
import com.sparta.spartalecture.Teacher.entity.Teacher;
import com.sparta.spartalecture.Teacher.service.TeacherService;
import com.sparta.spartalecture.course.dto.CourseRequestDto;
import com.sparta.spartalecture.course.dto.CreateCourseResponseDto;
import com.sparta.spartalecture.course.entity.Course;
import com.sparta.spartalecture.course.repository.CourseRepository;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final TeacherService teacherService;
    public CourseService(CourseRepository courseRepository, TeacherService teacherService) {
        this.courseRepository = courseRepository;
        this.teacherService = teacherService;
    }

    public CreateCourseResponseDto createCourse(CourseRequestDto courseRequestDto) {
        Teacher teacher = teacherService.getTeacherById(courseRequestDto.getTeacherId());
        Course course = new Course(courseRequestDto,teacher);
        courseRepository.save(course);
        return new CreateCourseResponseDto(course);
    }

}
