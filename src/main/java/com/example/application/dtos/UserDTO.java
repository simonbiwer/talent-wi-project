package com.example.application.dtos;

import java.time.LocalDate;


public class UserDTO{

    private int id;
    private String salutation;
    private String title;
    private String firstname;
    private String lastname;
    private LocalDate dateOfBirth;
    private String email;
    private String phone;
    private String password;

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    public void setTitle(String title) { this.title = title; }

    public void setFirstName(String firstname) {
        this.firstname = firstname;
    }

    public void setLastName(String lastname) {
        this.lastname = lastname;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public void setEmail(String email) { this.email = email; }

    public void setPassword(String password) { this.password = password; }

    public void setId(int id) { this.id = id; }

    public void setPhone(String phone) { this.phone = phone; }

    public String getSalutation() {
        return salutation;
    }

    public String getTitle() { return title; }

    public String getFirstName() {
        return this.firstname;
    }

    public String getLastName() {
        return this.lastname;
    }

    public LocalDate getDateOfBirth() { return this.dateOfBirth; }

    public String getEmail() { return this.email; }

    public String getPhone() { return this.phone; }

    public String getPassword() { return this.password; }

    public int getId() { return this.id; }

    public String toString() {
        return
                "id=" + id + '\'' +
                        ", salutation='" + salutation + '\'' +
                        ", title='" + title + '\'' +
                        ", firstname='" + firstname + '\'' +
                        ", lastname='" + lastname + '\'' +
                        ", dateOfBirth=" + dateOfBirth +
                        ", email='" + email + '\'' +
                        ", phone='" + phone + '\'';
    }
}

