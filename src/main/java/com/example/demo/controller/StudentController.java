package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping
    public ResponseEntity<Iterable<Student>> findAll(Pageable pageable) {
        return new ResponseEntity<>(studentService.findAll(pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity add(@RequestBody Student student) {
        studentService.save(student);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Student> findById(@PathVariable Long id) {
//        return new ResponseEntity<>(studentService.findById(id).get(), HttpStatus.OK);
//    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        Optional<Student> student1 = studentService.findById(id);
        student.setId(student1.get().getId());
        studentService.save(student);
        return new ResponseEntity<>(student1.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long id) {
        Optional<Student> student = studentService.findById(id);
        if (!student.isPresent()) {
            return new ResponseEntity<>(student.get(), HttpStatus.NOT_FOUND);
        }
        studentService.remove(id);
        return new ResponseEntity<>(student.get(), HttpStatus.NO_CONTENT);
    }


    @GetMapping("/search/{key}")
    public ResponseEntity<List<Student>> findByName(@PathVariable String key){
        List<Student> students = studentService.findAllByNameContaining(key);
        return new ResponseEntity<>(students,HttpStatus.OK);
    }

}
