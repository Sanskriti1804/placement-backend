package com.example.placement.dto.placement;

public class PlacementCoordinatorUpdateRequest {

    private String name;
    private String email;
    private String inMail;
    private String phoneNumber;

    public String getName() {
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

    public String getInMail() {
        return inMail;
    }

    public void setInMail(String inMail) {
        this.inMail = inMail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
