package com.example.application.Service.Email;

import jakarta.mail.MessagingException;

/**
 * Interface für den Email-Service
 */
public interface EmailService {

    void sendMail(final AbstractEmailContext email) throws MessagingException;
}
