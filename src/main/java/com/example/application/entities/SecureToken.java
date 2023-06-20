package com.example.application.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author tb 24.05.23
 * @since 24.05.23
 * Entität zum Speichern der Security Tokens für die Email-Verifizierung
 */


@Entity
@Table(name = "secureTokens",schema = "talent", catalog = "wi_projekt_mahbobi_sose2023")
public class SecureToken{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String token;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp timeStamp;

    @Column(updatable = false)
    @Basic(optional = false)
    private LocalDateTime expireAt;

    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName ="userid")
    private User user;

    @Transient
    private boolean isExpired;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(LocalDateTime expireAt) {
        this.expireAt = expireAt;
    }

    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    public boolean isExpired() {

        return getExpireAt().isBefore(LocalDateTime.now()); // this is generic implementation, you can always make it timezone specific
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
