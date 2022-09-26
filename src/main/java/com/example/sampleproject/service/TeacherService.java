package com.example.sampleproject.service;

import com.example.sampleproject.model.Teacher;

public interface TeacherService {
    Teacher createTeacher(Teacher teacher);

    Boolean isExistsTeacherByEmailId(String emailId);

    Boolean isExistsTeacherByPhoneNo(String phoneNo);
}
