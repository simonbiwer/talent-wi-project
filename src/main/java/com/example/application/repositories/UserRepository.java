package com.example.application.repositories;

import com.example.application.entities.User;
import com.example.application.dtos.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Repository Klasse für die Speicherung eines Users mit JPA
 * @author sb
 * @since 01.05.23
 */

@Component
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserByEmail(String email);
    User findUserByUserid(int userid);
    void deleteUserByUserid(int userid);
}
