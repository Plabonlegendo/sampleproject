package com.example.sampleproject.service;

import com.example.sampleproject.model.Student;

public interface StudentService {
    Student createStudent(Student student);

    Boolean isExistsStudentByEmailId(String emailId);

    Boolean isExistsStudentByPhoneNo(String phoneNo);
}
