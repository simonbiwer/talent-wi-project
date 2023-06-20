package com.example.application.Service.Email;

import com.example.application.entities.User;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author tb 24.05.23
 * @since 24.05.23
 * Implementierung des EmailContext mit den benötigten Daten (User-Vorname, User-Email, Versendungsemail)
 */

public class AccountVerificationEmailContext extends AbstractEmailContext {

    private String token;


    @Override
    public <T> void init(T context){
        //we can do any common configuration setup here
        // like setting up some base URL and context
        User customer = (User) context; // we pass the customer informati
        put("firstName", customer.getVorname());
        setTemplateLocation("emails/email-verification");
        setSubject("Complete your registration");
        setFrom("no-reply-talent-innotech@web.de");
        setTo(customer.getEmail());
    }

    public void setToken(String token) {
        this.token = token;
        put("token", token);
    }

    public void buildVerificationUrl(final String baseURL, final String token){
        final String url= UriComponentsBuilder.fromHttpUrl(baseURL)
                .path("/register/verify").queryParam("token", token).toUriString();
        put("verificationURL", url);
    }
}
