package com.example.student_file_app.service;

import com.example.student_file_app.model.Student;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class StudentService {

    private List<Student> students = new ArrayList<>();
    private Long nextId;

    @PostConstruct
    public void loadFromFile() {
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            Objects.requireNonNull(
                                    getClass().getClassLoader()
                                            .getResourceAsStream("students.txt")        //convert file into stream
                            )
                    )
            );

            String line;
            long maxId = 0;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                Long id = Long.parseLong(parts[0].trim());
                String name = parts[1].trim();
                String email = parts[2].trim();
                students.add(new Student(id,name,email));
                if (id > maxId) maxId = id;
            }

            nextId = maxId + 1; //next Id auto set
            reader.close();
        }   catch (Exception e) {
            System.out.println("File load error: " + e.getMessage());
        }
    }

    public List<Student> getAll()   {return students;}

    public Student getById(Long id) {
        return students.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Student create(Student s) {
        s.setId(nextId++);
        students.add(s);
        return s;
    }

    public Student update(Long id, Student updated) {
        Student s = getById(id);
        if (s == null) return null;
        s.setName(updated.getName());
        s.setEmail(updated.getEmail());
        return s;
    }

    public boolean delete(Long id) {
        return students.removeIf(s -> s.getId().equals(id));
    }
}
