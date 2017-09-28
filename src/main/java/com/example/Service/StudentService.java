package com.example.Service;

import com.example.Entity.Student;
import com.example.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by FateXRebirth on 25/09/2017.
 */

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public void insertStudent(Student student) {
        studentRepository.insertStudent(student);
    }

    public Collection<Student> getAllStudents() {
        return studentRepository.getAllStudents();
    }

    public Student getStudentById(String id) {
        return studentRepository.getStudentById(id);
    }

    public void updateStudent(Student student) {
        studentRepository.updateStudent(student);
    }

    public void removeStudentById(String id) {
        this.studentRepository.removeStudentById(id);
    }

}
