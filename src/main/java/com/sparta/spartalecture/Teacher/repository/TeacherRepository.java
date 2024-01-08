package com.sparta.spartalecture.Teacher.repository;

import com.sparta.spartalecture.Teacher.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher,Long> {
}
