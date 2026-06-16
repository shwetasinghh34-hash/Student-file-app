package com.example.student_file_app.model;

import jakarta.persistence.*;

//class represent a database table
@Entity
@Table(name = "students")
public class Student {

    //primary key of the table
    @Id
    //Mysql will handle auto increment
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    public Student() {
    }

    public Student(String name, String email) {
        this.name = name;
        this.email = email;
    }

    //Getter and Setter for id
    public Long getId()             { return id; }
    public void setId(Long id)      { this.id = id; }

    //Getter and Setter for Name
    public String getName()             { return name; }
    public void setName(String name)    { this.name = name; }

    //Getter and Setter for email
    public String getEmail()                { return email; }
    public void setEmail(String email)      {this.email = email;}
}
