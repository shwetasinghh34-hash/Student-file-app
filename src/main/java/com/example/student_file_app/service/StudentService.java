package com.example.student_file_app.service;

import com.example.student_file_app.model.Student;
import com.example.student_file_app.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    public List<Student> getAll() {
        return repository.findAll();
    }

    public Student getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Student create(Student student) {
        return repository.save(student);
    }

    public Student update(Long id, Student updated) {
        Student student = getById(id);

        if (student == null) {
            return null;
    }

        //Update the fields with new values
        student.setName(updated.getName());
        student.setEmail(updated.getEmail());

        return repository.save(student);
    }
    public boolean delete(Long id) {
        if (!repository.existsById(id)) {
            return false;
        }

        //deleteById() runs: DELETE FROM students WHERE id=?
        repository.deleteById(id);
        return true;
    }
}
