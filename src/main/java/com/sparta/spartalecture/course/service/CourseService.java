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
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final TeacherService teacherService;
    private final UserService userService;

    @Transactional
    public CourseResponseDto createCourse(CourseRequestDto courseRequestDto) {
        Teacher teacher = teacherService.getTeacherById(courseRequestDto.getTeacherId());
        Course course = new Course(courseRequestDto, teacher);
        courseRepository.save(course);
        return new CourseResponseDto(course);
    }

    public Course getCourseById(long id) {
        return courseRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("아이디에 해당하는 강의가 없습니다."));
    }

    public List<CourseResponseDto> getCoursesByCategory(String category, String sortBy, boolean isAsc) {
        List<String> validSortByFields = Arrays.asList("lecture", "price", "addDate");
        if (!validSortByFields.contains(sortBy)) {
            throw new IllegalArgumentException("지원하지 않는 정렬 방식입니다.");
        }

        Sort sort = Sort.by(sortBy);
        sort = isAsc? sort.ascending() : sort.descending();
        return courseRepository.findAllByCategory(category, sort)
                .stream().map(CourseResponseDto::new).toList();
    }

    public CourseDetailResponseDto getCourseDetailById(long courseId) {
        return new CourseDetailResponseDto(getCourseById(courseId));
    }

    @Transactional
    public LikeResponseDto updateLike(long userId, long courseId) {
        Course course = getCourseById(courseId);
        List<User> users = course.getUsers();
        Optional<User> user = users.stream().filter(u->u.getId() == userId).findFirst();

        if (user.isPresent()) {
            users.remove(user.get());
            return new LikeResponseDto("취소");
        } else {
            User new_user = userService.getUserById(userId);
            users.add(new_user);
            return new LikeResponseDto("등록");
        }
    }
}
