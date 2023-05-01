package com.example.application.dtos;

/**
 * Interface für das UserDTO
 * @author sb
 * @since 01.05.23
 */

public interface UserDTO {
    int getId();
    void setId(int id);
    String getFirstname();
    void setFirstname(String firstname);
    String getLastname();
    void setLastname(String lastname);
    String getEmail();
    void setEmail(String email);
    String getPassword();
    void setPassword(String password);
}
