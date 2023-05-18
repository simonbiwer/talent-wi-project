package com.example.application.entities;

import com.example.application.entities.SecureToken;
import com.example.application.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SecureTokenTest {

    private SecureToken secureToken;

    @Mock
    private User mockUser;

    @BeforeEach
    void setUp() {
        secureToken = new SecureToken();
        secureToken.setToken("testToken");
        secureToken.setExpireAt(LocalDateTime.now().plusHours(1));
        secureToken.setUser(mockUser);
    }

    @Test
    void getToken() {
        assertEquals("testToken", secureToken.getToken());
    }

    @Test
    void setToken() {
        secureToken.setToken("newToken");
        assertEquals("newToken", secureToken.getToken());
    }

    @Test
    void getExpireAt() {
        LocalDateTime expireAt = LocalDateTime.now().plusHours(1);
        assertEquals(expireAt, secureToken.getExpireAt());
    }

    @Test
    void setExpireAt() {
        LocalDateTime newExpireAt = LocalDateTime.now().plusDays(1);
        secureToken.setExpireAt(newExpireAt);
        assertEquals(newExpireAt, secureToken.getExpireAt());
    }

    @Test
    void isExpired() {
        LocalDateTime expireAt = LocalDateTime.now().minusHours(1);
        secureToken.setExpireAt(expireAt);
        assertTrue(secureToken.isExpired());
    }

    @Test
    void isNotExpired() {
        LocalDateTime expireAt = LocalDateTime.now().plusHours(1);
        secureToken.setExpireAt(expireAt);
        assertFalse(secureToken.isExpired());
    }

    @Test
    void getUser() {
        assertEquals(mockUser, secureToken.getUser());
    }

    @Test
    void setUser() {
        User newUser = mock(User.class);
        secureToken.setUser(newUser);
        assertEquals(newUser, secureToken.getUser());
    }
}
