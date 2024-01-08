package com.sparta.spartalecture.teacher.repository;


import com.sparta.spartalecture.teacher.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher,Long> {
}
