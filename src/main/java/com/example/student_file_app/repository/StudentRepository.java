package com.example.student_file_app.repository;

import com.example.student_file_app.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    //JpaRepository already provides CRUD methods
}
