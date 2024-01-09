package com.sparta.spartalecture.service;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.sparta.spartalecture.dto.CourseRequestDto;
import com.sparta.spartalecture.dto.CourseResponseDto;
import com.sparta.spartalecture.dto.LikeRequestDto;
import com.sparta.spartalecture.dto.LikeResponseDto;
import com.sparta.spartalecture.entity.Course;
import com.sparta.spartalecture.entity.Teacher;
import com.sparta.spartalecture.entity.Like;
import com.sparta.spartalecture.entity.User;
import com.sparta.spartalecture.repository.CourseRepository;
import com.sparta.spartalecture.repository.LikeRepository;
import com.sparta.spartalecture.repository.TeacherRepository;
import com.sparta.spartalecture.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;

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

    public LikeResponseDto updateLike(LikeRequestDto likeRequestDto) {
        User user = userRepository.findById(likeRequestDto.getUserId()).orElseThrow();
        Course course = courseRepository.findById(likeRequestDto.getCourseId()).orElseThrow();
        Like existingLike = likeRepository.findByUserAndCourse(user, course);
        if (existingLike == null) {
            Like newLike = new Like(course,user);
            newLike.setLikeCount(1L);
            likeRepository.save(newLike);
            return new LikeResponseDto(newLike.getLikeCount());
        } else {
            long currentLikeCount = existingLike.getLikeCount();
            likeRepository.delete(existingLike);
            existingLike.setLikeCount(currentLikeCount - 1);
            return new LikeResponseDto(existingLike.getLikeCount());
        }
    }
}
