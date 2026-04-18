package com.example.placement.dto.company;

import com.example.placement.common.enums.PreferredContactMode;

public class CompanyContactSupportResponse {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private PreferredContactMode preferredMode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public PreferredContactMode getPreferredMode() {
        return preferredMode;
    }

    public void setPreferredMode(PreferredContactMode preferredMode) {
        this.preferredMode = preferredMode;
    }
}
