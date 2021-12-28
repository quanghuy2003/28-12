package com.example.demo.service;

import com.example.demo.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IStudentService extends IService <Student> {
    List<Student> findAllByNameContaining(String name);
    Page<Student> findAllByScoreGreaterThan8(Pageable pageable, Double score);
}
