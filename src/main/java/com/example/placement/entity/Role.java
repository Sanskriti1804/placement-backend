package com.example.placement.entity;

import jakarta.persistence.*;

@Entity //
@Table(name = "roles")
public  class Role {
    @Id      //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)     //auto generated id using auto increment
    private Long id;

    @Enumerated(EnumType.STRING)    //stores enum as string
    @Column(nullable = false, unique = true)
    private RoleType roleName;

    public Role(){}     //default constructor - required by JPA

    public Role(Long id, RoleType roleName){    //parameterized constructor
        this.id = id;
        this.roleName = roleName;
    }

    //GETTERS AND SETTERS
    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public RoleType getRoleName(){
        return roleName;
    }

    public void setRoleName(RoleType roleName){

        this.roleName = roleName;
    }

}