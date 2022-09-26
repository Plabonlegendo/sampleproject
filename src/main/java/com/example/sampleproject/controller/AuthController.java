package com.example.sampleproject.controller;

import com.example.sampleproject.dtos.MessageResponse;
import com.example.sampleproject.dtos.SignUpRequest;
import com.example.sampleproject.model.Student;
import com.example.sampleproject.model.Teacher;
import com.example.sampleproject.service.StudentService;
import com.example.sampleproject.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/v1")
public class AuthController {
    private final StudentService studentService;

    private final TeacherService teacherService;


    @Autowired
    PasswordEncoder encoder;


    public AuthController(StudentService studentService, TeacherService teacherService){
        this.studentService = studentService;
        this.teacherService = teacherService;
    }

    @PostMapping("auth/signup/teacher")
    public ResponseEntity<?> registerTeacher(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (teacherService.isExistsTeacherByPhoneNo(signUpRequest.getPhoneNo()) || studentService.isExistsStudentByPhoneNo(signUpRequest.getPhoneNo())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Phone No is already taken!"));
        }

        if (teacherService.isExistsTeacherByEmailId(signUpRequest.getEmail()) || studentService.isExistsStudentByEmailId(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        Teacher teacher = new Teacher(signUpRequest.getUsername(),
                signUpRequest.getPhoneNo(), signUpRequest.getEmail(),
                signUpRequest.getDepartmentName(), signUpRequest.getRole(),
                encoder.encode(signUpRequest.getPassword()), true);

        teacherService.createTeacher(teacher);

        return ResponseEntity.ok(new MessageResponse("Teacher registered successfully!"));
    }
    @PostMapping("auth/signup/student")
    public ResponseEntity<?> registerStudent(@Valid @RequestBody SignUpRequest signUpRequest){
        if(studentService.isExistsStudentByPhoneNo(signUpRequest.getPhoneNo()) || teacherService.isExistsTeacherByPhoneNo(signUpRequest.getPhoneNo())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Phone No is already taken!"));
        }
        if(studentService.isExistsStudentByEmailId(signUpRequest.getEmail()) || teacherService.isExistsTeacherByEmailId(signUpRequest.getEmail())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already taken!"));
        }

        Student student = new Student(signUpRequest.getUsername(),
                signUpRequest.getPhoneNo(), signUpRequest.getEmail(),
                signUpRequest.getDepartmentName(), signUpRequest.getRole(),
                encoder.encode(signUpRequest.getPassword()), true);

        studentService.createStudent(student);

        return ResponseEntity.ok(new MessageResponse("Student Registered Successfully"));
    }


}
