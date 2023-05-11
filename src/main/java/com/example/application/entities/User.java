package com.example.application.entities;

import jakarta.persistence.*;

import java.util.Set;

/**
 * Eine Entity-Klasse, die einen Benutzer darstellt.
 */
@Entity(name = "user")
@Table(name="user", schema= "talent")
public class User {
    /**
     * Die ID des Benutzers.
     */
    @Id
    @SequenceGenerator(name = "User_id_use_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private int userid;

    /**
     * Gibt die ID des Benutzers zurück.
     * @return die ID des Benutzers
     */
    public int getUserid() {
        return userid;
    }

    /**
     * Setzt die ID des Benutzers.
     * @param userid die neue ID des Benutzers
     */
    public void setUserid(int userid) {
        this.userid = userid;
    }

    /**
     * Die E-Mail-Adresse des Benutzers.
     */
    @Basic
    @Column(name = "email")
    private String email;

    /**
     * Gibt die E-Mail-Adresse des Benutzers zurück.
     * @return die E-Mail-Adresse des Benutzers
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setzt die E-Mail-Adresse des Benutzers.
     * @param email die neue E-Mail-Adresse des Benutzers
     */
    public void setEmail(String email){
        this.email = email;
    }

    /**
     * Der Vorname des Benutzers.
     */
    @Basic
    @Column(name = "vorname")
    private String vorname;

    /**
     * Gibt den Vorname des Benutzers zurück.
     * @return der Vorname des Benutzers
     */
    public String getVorname() {
        return vorname;
    }

    /**
     * Setzt den Vorname des Benutzers.
     * @param vorname der neue Vorname des Benutzers
     */
    public void setVorname(String vorname){
        this.vorname = vorname;
    }

    /**
     * Der Nachname des Benutzers.
     */
    @Basic
    @Column(name = "nachname")
    private String nachname;

    /**
     * Gibt den Nachname des Benutzers zurück.
     * @return der Nachname des Benutzers
     */
    public String getNachname() {
        return nachname;
    }

    /**
     * Setzt den Nachname des Benutzers.
     * @param nachname der neue Nachname des Benutzers
     */
    public void setNachname(String nachname){
        this.nachname = nachname;
    }

    /**
     * Das Passwort des Benutzers.
     */
    @Basic
    @Column(name = "passwort")
    private String passwort;

    /**
     * Gibt das Passwort des Benutzers zurück.
     * @return das Passwort des Benutzers
     */
    public String getPasswort() {
        return passwort;
    }

    /**
     * Setzt das Passwort des Benutzers.
     * @param passwort das neue Passwort des Benutzers
     */
    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }


    /**
     * Boolean Attribut, zur Verifizierung der Email bzw. des Accounts
     * default ist false
     */
    @Column(name = "verified")
    private boolean verified = false;

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    @OneToMany(mappedBy = "user")
    private Set<SecureToken> tokens;
}
