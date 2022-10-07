package com.register.dao;

import com.register.model.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class StudentRepositoryImpl implements StudentRepository{

    @PersistenceContext
    EntityManager entityManager;

    //2.1). add a new student along with their course registrations.
    @Override
    public void addStudent(Student student) {
        entityManager.persist(student);
    }

    //2.2). Delete a student.
    @Override
    public void removeStudent(Student student) {
        entityManager.remove(student);
    }

    //2.3).Get all students, sorted by their name, for a given course with course name as input.
    @Override
    public List<Student> findAllByCourseName(String courseName) {
        List<Student> students = entityManager.createQuery("SELECT s FROM Student s, IN(s.courses) AS c where c.name =:courseName ORDER BY s.name", Student.class).setParameter("courseName", courseName).getResultList();
        return students;
    }

    //2.5). How to find all students who don’t register for a given course?
    //Another approach (Not recommended)- use native SQL query but it is not recommended as it will return List instead of List<Student> i.e not type safe. Also, Not efficient.
    //List students = entityManager.createNativeQuery("SELECT * FROM student s INNER JOIN registration r ON s.id = r.student_id WHERE r.student_id NOT IN (SELECT ins.student_id FROM registration ins INNER JOIN course c ON ins.student_id = c.id where c.name = :courseName)", Student.class).setParameter("courseName", courseName).getResultList();
    //I have used JPQL because it is type safe and more efficient.
    @Override
    public List<Student> findAllNotMatchedByCourseName(String courseName) {
        List<Student> students = entityManager.createQuery("SELECT s FROM Student s where s NOT IN (SELECT ins FROM Student ins, IN(ins.courses) AS c where c.name =:courseName)", Student.class).setParameter("courseName", courseName).getResultList();
        return students;
    }
}
