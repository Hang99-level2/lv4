package com.sparta.spartalecture.course.repository;

import com.sparta.spartalecture.course.entity.Course;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    List<Course> findAllByCategory(String category, Sort sort);
}
