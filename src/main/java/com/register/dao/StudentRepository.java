package com.register.dao;


import com.register.model.Student;

import java.util.List;

public interface StudentRepository {

    void addStudent(Student student);

    void removeStudent(Student student);

    List<Student> findAllByCourseName(String courseName);

    List<Student> findAllNotMatchedByCourseName(String courseName);
}
