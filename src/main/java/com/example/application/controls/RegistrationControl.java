package com.example.application.controls;

import ch.qos.logback.core.model.Model;
import com.example.application.Exception.InvalidTokenException;
import com.example.application.Service.Token.SecureTokenService;
import com.example.application.Service.User.UserService;
import com.example.application.controls.factories.UserFactory;
import com.example.application.dtos.impl.RegistrationResultDTOImpl;
import com.example.application.dtos.impl.UserDTOImpl;
import com.example.application.entities.SecureToken;
import com.example.application.entities.User;
import com.example.application.repositories.SecureTokenRepository;
import com.example.application.repositories.UserRepository;
import com.example.application.utils.Globals;
import com.vaadin.flow.router.Route;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Control-Klasse zum Abwickeln der Registrierung
 * @author sb
 * @since 01.05.23
 */

@Controller
@RequestMapping("register")
public class RegistrationControl {

    private static final String REDIRECT_LOGIN= "redirect:/login";

    @Autowired
    private UserService userService;

    @Resource
    private MessageSource messageSource;

    /**
     * Registriert einen neuen Benutzer, falls die übergebene Email noch nicht in der Datenbank existiert.
     * @param userDTOImpl - ein UserDTOImpl-Objekt, das Informationen über den neuen Benutzer enthält.
     * @return ein RegistrationResultDTOImpl-Objekt, das den Erfolg der Registrierung und eine Nachricht enthält.
     */
    public RegistrationResultDTOImpl registerUser(UserDTOImpl userDTOImpl){
        return userService.registerUser(userDTOImpl);
    }

    /**
     * Überprüft, ob eine bestimmte Email bereits in der Datenbank existiert.
     * @param email - die Email-Adresse, die überprüft werden soll.
     * @return true, wenn die Email bereits in der Datenbank existiert, andernfalls false.
     */
    public boolean checkIfEmailOccupied(String email) {
        return userService.checkIfEmailOccupied(email);
    }


    @GetMapping("/verify")
    public String verifyCustomer(@RequestParam(required = false) String token, final Model model, RedirectAttributes redirAttr){
        if(StringUtils.isEmpty(token)){
            redirAttr.addFlashAttribute("tokenError", messageSource.getMessage("user.registration.verification.missing.token", null, LocaleContextHolder.getLocale()));
            return REDIRECT_LOGIN;
        }
        try {
            userService.verifyUser(token);
        } catch (InvalidTokenException e) {
            redirAttr.addFlashAttribute("tokenError", messageSource.getMessage("user.registration.verification.invalid.token", null,LocaleContextHolder.getLocale()));
            return REDIRECT_LOGIN;
        }

        redirAttr.addFlashAttribute("verifiedAccountMsg", messageSource.getMessage("user.registration.verification.success", null,LocaleContextHolder.getLocale()));
        return REDIRECT_LOGIN;
    }


}