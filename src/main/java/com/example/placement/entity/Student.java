//package com.example.placement.entity;
//
//
//import jakarta.persistence.*;
//
//@Entity
//@Table(name = "studemts")
//public class Student {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(unique = true, nullable = false)
//    private String username;
//
//    private String name;
//
//    @Enumerated(EnumType.STRING)
//    private Role role;
//
//    public Student(){}
//
//    public Student(String username, String name, Role role){
//        this.username = username;
//        this.name = name;
//        this.role = role;
//    }
//
//    public Long getId(){
//        return id;
//    }
//
//    public String getUsername(){
//        return username;
//    }
//
//    public String getName(){
//        return name;
//    }
//
//    public Role getRole(){
//        return role;
//    }
//
//    public void setUsername(String username){
//        this.username = username;
//    }
//
//    public void setName(String name){
//        this.name = name;
//    }
//
//    public void setRole(Role role){
//        this.role = role;
//    }
//}
