package com.sparta.spartalecture.repository;

import com.sparta.spartalecture.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course,Long> {
    Optional<Course> findByCategory(String category);
}