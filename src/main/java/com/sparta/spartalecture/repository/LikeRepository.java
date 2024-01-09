package com.sparta.spartalecture.repository;

import com.sparta.spartalecture.entity.Course;
import com.sparta.spartalecture.entity.Like;
import com.sparta.spartalecture.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like,Long> {
    Like findByUserAndCourse(User user, Course course);
}
