package com.example.application.entities;

import jakarta.persistence.*;
// import javax.persistence.*;
import java.util.Objects;
import java.util.Collection;

/**
 * Implementation des User Entitätsklasse
 * last edited: sb 02.05.23
 */

@Entity(name = "User")
@Table(name="user", schema= "public")
public class User {

    // Id des Users, automatisch generiert
    @Id
    @SequenceGenerator(name = "User_id_use_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private int id_user;

    // Getter und Setter für die ID
    public int getUserid() {
        return id_user;
    }

    public void setUserid(int userid) {
        this.id_user = userid;
    }

    // E-Mail des Users
    @Basic
    @Column(name = "email")
    private String email;

    // Getter und Setter für die E-Mail
    public String getEmail() {
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    // Vorname des Users
    @Basic
    @Column(name = "vorname")
    private String vorname;

    // Getter und Setter für den Vornamen
    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname){
        this.vorname = vorname;
    }

    // Nachname des Users
    @Basic
    @Column(name = "nachname")
    private String nachname;

    // Getter und Setter für den Nachnamen
    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname){
        this.nachname = nachname;
    }

    // Passwort des Users
    @Basic
    @Column(name = "passwort")
    private String passwort;

    // Getter und Setter für das Passwort
    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }
}
