//package com.example.placement.controller;
//
//import com.example.placement.entity.Student;
//import com.example.placement.repository.StudentRepo;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/students")
//public class StudentController {
//
//    private final StudentRepo repository;
//
//    public StudentController(StudentRepo repo, StudentRepo repository){
//        this.repository = repository;
//    }
//
//    @PostMapping
//    public Student addStudent(@RequestBody Student student){
//        return repository.save(student);
//    }
//
//    @GetMapping
//    public List<Student> getStudents(){
//        return repository.findAll();
//    }
//
//}
//
//
