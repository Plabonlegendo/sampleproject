package com.example.sampleproject.service.Impl;

import com.example.sampleproject.model.Student;
import com.example.sampleproject.repository.StudentRepository;
import com.example.sampleproject.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @Override
    public Student createStudent(Student student){
        try{
            return studentRepository.save(student);
        }catch(RuntimeException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean isExistsStudentByPhoneNo(String phoneNo) {
        return studentRepository.existsByPhoneNo(phoneNo);
    }

    @Override
    public Boolean isExistsStudentByEmailId(String emailId) {
        return studentRepository.existsByEmailId(emailId);
    }
}
