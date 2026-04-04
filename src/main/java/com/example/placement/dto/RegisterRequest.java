package com.example.placement.dto;

//DTO - data transfer object - carries registeration data from the client to the backend
public class RegisterRequest {

    //fields that the client sends when registering a user
    private String name;
    private String email;
    private String password;
    private String phone;

    //getters and setters
    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
