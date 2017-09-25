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

    public Collection<Student> getAllStudents() {
        return this.studentRepository.getAllStudents();
    }
}
