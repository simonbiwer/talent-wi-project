package com.example.application.entities;

import java.util.Objects;
import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="user", schema= "wi_projekt_mahbobi_sose2023", catalog= "wi_projekt_mahbobi_sose2023")
public class User {
    @Id
    @SequenceGenerator(name = "user_userid_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_use")
    private int id_use;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "vorname")
    private String vorname;
    @Basic
    @Column(name = "nachname")
    private String nachname;
    @Basic
    @Column(name = "passwort")
    private String passwort;


    public int getUserid() {
        return id_use;
    }

    public void setUserid(int userid) {
        this.id_use = userid;
    }

    public String getEmailadresse() {
        return email;
    }

    public void setEmailadresse(String emailadresse) {
        this.email = emailadresse;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }
}
