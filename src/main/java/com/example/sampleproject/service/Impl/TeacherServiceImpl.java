package com.example.sampleproject.service.Impl;

import com.example.sampleproject.model.Teacher;
import com.example.sampleproject.repository.TeacherRepository;
import com.example.sampleproject.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private final TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository){
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Teacher createTeacher(Teacher teacher) {
        try{
            return teacherRepository.save(teacher);
        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean isExistsTeacherByEmailId(String emailId) {
        return teacherRepository.existsByEmailId(emailId);
    }

    @Override
    public Boolean isExistsTeacherByPhoneNo(String phoneNo) {
        return teacherRepository.existsByPhoneNo(phoneNo);
    }
}
