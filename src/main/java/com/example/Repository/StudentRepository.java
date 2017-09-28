package com.example.Repository;

import com.example.Entity.Student;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by FateXRebirth on 25/09/2017.
 */

@Repository
public class StudentRepository {

    private static Map<String, Student> students;

    static {
        students = new HashMap<String, Student>() {
            {
                put("1", new Student("1", "Said", "Computer Science"));
                put("2", new Student("2", "Alex U", "Finance"));
                put("3", new Student("3", "Anna", "Maths"));

            }

        };
    }

    public void insertStudent(Student student) {
        students.put(student.getId(), student);
    }

    public Collection<Student> getAllStudents() {

        return this.students.values();
    }

    public Student getStudentById(String id) {
        return this.students.get(id);
    }

    public void updateStudent(Student student) {
        Student s = students.get(student.getId());
        s.setCourse(student.getCourse());
        s.setName(student.getName());
        students.put(student.getId(), s);
    }

    public void removeStudentById(String id) {
        this.students.remove(id);
    }

}
