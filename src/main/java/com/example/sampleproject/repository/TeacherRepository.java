package com.example.sampleproject.repository;

import com.example.sampleproject.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    Optional<Teacher> findByEmailId(String emailId);
    Boolean existsByEmailId(String emailId);

    Boolean existsByPhoneNo(String phoneNo);
}
