package com.example.sampleproject.repository;

import com.example.sampleproject.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Boolean existsByEmailId(String emailId);

    Boolean existsByPhoneNo(String phoneNo);
}
