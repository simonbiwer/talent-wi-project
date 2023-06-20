package com.example.application.repositories;

import com.example.application.entities.SecureToken;
import com.example.application.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecureTokenRepository extends JpaRepository<SecureToken, Long> {

    SecureToken findByToken(final String token);
    Long removeByToken(String token);
    void removeSecureTokenByUser(User user);
}