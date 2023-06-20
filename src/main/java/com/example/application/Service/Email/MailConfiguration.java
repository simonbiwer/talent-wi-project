package com.example.application.Service.Email;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * @author tb 24.05.23
 * @since 24.05.23
 * Konfiguration des Email-Servers, sodass die Email über den Unternehmensaccount gesendet werden kann.
 */

@Configuration
public class MailConfiguration {

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.web.de");
        mailSender.setPort(587);

        mailSender.setUsername("no-reply-talent-innotech@web.de");
        mailSender.setPassword("MahbobiWS2023");

        Properties props = mailSender.getJavaMailProperties();
//        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        return mailSender;
    }
}