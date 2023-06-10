package com.example.application.controls;
import com.example.application.entities.User;
import com.example.application.repositories.StellenanzeigeRepository;
import com.example.application.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

/**
 * Dies ist die Testklasse für die UserControl-Klasse.
 * @author  Yarosh
 * @since 10.05.2023
 */


class UserControlTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private StellenanzeigeRepository stellenanzeigeRepository;

    @InjectMocks
    private UserControl userControl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deleteUser_SollteBenutzerUndZugehörigeStellenanzeigeLöschen() {
        // Arrange
        int userId = 1;
        User user = new User();
        // Mocken des Aufrufs von userRepository.findUserByUserid(userId)
        when(userRepository.findUserByUserid(userId)).thenReturn(user);

        // Act
        userControl.deleteUser(userId);

        // Assert
        // Überprüfen, ob stellenanzeigeRepository.deleteStellenanzeigesByUserid(user) einmal aufgerufen wurde
        verify(stellenanzeigeRepository, times(1)).deleteStellenanzeigesByUserid(user);
        // Überprüfen, ob userRepository.deleteUserByUserid(userId) einmal aufgerufen wurde
        verify(userRepository, times(1)).deleteUserByUserid(userId);
    }
}
