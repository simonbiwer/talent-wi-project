package com.example.application.entities;

import jakarta.persistence.*;

@Entity(name = "user")
@Table(name="user", schema= "talent")
public class User {
    @Id
    @SequenceGenerator(name = "User_id_use_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private int userid;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @Basic
    @Column(name = "email")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    @Basic
    @Column(name = "vorname")
    private String vorname;


    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname){
        this.vorname = vorname;
    }

    @Basic
    @Column(name = "nachname")
    private String nachname;

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname){
        this.nachname = nachname;
    }

    @Basic
    @Column(name = "passwort")
    private String passwort;

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }
}
