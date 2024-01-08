package com.sparta.spartalecture.course.service;


import com.sparta.spartalecture.course.dto.CourseDetailResponseDto;
import com.sparta.spartalecture.course.dto.CourseRequestDto;
import com.sparta.spartalecture.course.dto.CourseResponseDto;
import com.sparta.spartalecture.course.dto.LikeResponseDto;
import com.sparta.spartalecture.course.entity.Course;
import com.sparta.spartalecture.course.repository.CourseRepository;
import com.sparta.spartalecture.teacher.entity.Teacher;
import com.sparta.spartalecture.teacher.service.TeacherService;
import com.sparta.spartalecture.user.entity.User;
import com.sparta.spartalecture.user.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final TeacherService teacherService;
    private final UserService userService;

    public CourseService(CourseRepository courseRepository, TeacherService teacherService, UserService userService) {
        this.courseRepository = courseRepository;
        this.teacherService = teacherService;
        this.userService = userService;
    }
    public Course getCourseById(long id) {
        return courseRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("아이디에 해당하는 강의가 없습니다."));
    }

    public CourseResponseDto createCourse(CourseRequestDto courseRequestDto) {
        Teacher teacher = teacherService.getTeacherById(courseRequestDto.getTeacherId());
        Course course = new Course(courseRequestDto, teacher);
        courseRepository.save(course);
        return new CourseResponseDto(course);
    }

    public List<CourseResponseDto> getCoursesByCategory(String category, String sortBy, boolean isAsc) {
        Sort sort = Sort.by(sortBy);
        if (!isAsc) {
            sort = sort.descending();
        }
        return courseRepository.findAllByCategory(category, sort).stream().map(CourseResponseDto::new).toList();
    }

    @Transactional
    public LikeResponseDto updateLike(long userId, long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow();
        List<User> users = course.getUsers();
        boolean isLike = false;

        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (userId == user.getId()) {
                isLike = true;
                iterator.remove();
                break;
            }
        }
        if (!isLike) {
            User user = userService.getUserById(userId);
            users.add(user);
            return new LikeResponseDto("등록");
        } else {
            return new LikeResponseDto("취소");
        }
    }

    public CourseDetailResponseDto getCourseDetailById(long courseId) {
        Course course = getCourseById(courseId);
        return new CourseDetailResponseDto(course);
    }
}
