package com.example.application.Service.User;



import com.example.application.Exception.InvalidTokenException;
import com.example.application.Exception.UnknownIdentifierException;
import com.example.application.Exception.UserAlreadyExistException;
import com.example.application.Service.Email.AccountVerificationEmailContext;
import com.example.application.Service.Email.EmailService;
import com.example.application.Service.Token.SecureTokenService;
import com.example.application.controls.factories.UserFactory;
import com.example.application.dtos.impl.RegistrationResultDTOImpl;
import com.example.application.dtos.impl.UserDTOImpl;
import com.example.application.entities.SecureToken;
import com.example.application.entities.User;
import com.example.application.repositories.SecureTokenRepository;
import com.example.application.repositories.UserRepository;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import jakarta.mail.MessagingException;

import java.util.Map;
import java.util.Objects;

@Service("userService")
public class DefaultUserService implements UserService{

    @Autowired
    private UserRepository userRep;


    @Resource
    private EmailService emailService;

    @Autowired
    private SecureTokenService secureTokenService;

    @Autowired
    SecureTokenRepository secureTokenRepository;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Value("${site.base.url.https}")
    private String baseURL;



    /**
     * Registriert einen neuen Benutzer, falls die übergebene Email noch nicht in der Datenbank existiert.
     * @param userDTOImpl - ein UserDTOImpl-Objekt, das Informationen über den neuen Benutzer enthält.
     * @return ein RegistrationResultDTOImpl-Objekt, das den Erfolg der Registrierung und eine Nachricht enthält.
     */
    public RegistrationResultDTOImpl registerUser(UserDTOImpl userDTOImpl){
        RegistrationResultDTOImpl result = new RegistrationResultDTOImpl();
        if (checkIfEmailOccupied(userDTOImpl.getEmail())){
            result.setMessage("Diese Email ist bereits vergeben");
            result.setNotOK();
        }
        if (result.OK()){
            User userEntity = UserFactory.createUser(userDTOImpl);
            try{
                userRep.save(userEntity);
                sendRegistrationConfirmationEmail(userEntity);
            } catch (Exception e){
                result.setNotOK();
                result.setMessage("Fehler beim Abspeichern in die Datenbank: Admin kontaktieren");
                userRep.delete(userEntity);
            }

            if (result.OK()) {
                result.setMessage("Registrierung erfolgreich!");
            }
        }
        return result;
    }

    /**
     * Überprüft, ob eine bestimmte Email bereits in der Datenbank existiert.
     * @param email - die Email-Adresse, die überprüft werden soll.
     * @return true, wenn die Email bereits in der Datenbank existiert, andernfalls false.
     */
    public boolean checkIfEmailOccupied(String email) {
        //Datenbank Zugriff mit boolean Wert ob email bereits in der Datenbank existiert
        return !(userRep.findUserByEmail(email) == null);
    }



    @Override
    public void sendRegistrationConfirmationEmail(User user) {
        SecureToken secureToken= secureTokenService.createSecureToken();
        secureToken.setUser(user);
        secureTokenRepository.save(secureToken);
        AccountVerificationEmailContext emailContext = new AccountVerificationEmailContext();
        emailContext.init(user);
        emailContext.setToken(secureToken.getToken());
        emailContext.buildVerificationUrl(baseURL, secureToken.getToken());
        try {
            emailService.sendMail(emailContext);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean verifyUser(String token) throws InvalidTokenException {
        SecureToken secureToken = secureTokenService.findByToken(token);
        if(Objects.isNull(secureToken) || !StringUtils.equals(token, secureToken.getToken()) || secureToken.isExpired()){
            throw new InvalidTokenException("Token is not valid");
        }
        User user = userRep.getOne(secureToken.getUser().getUserid());
        if(Objects.isNull(user)){
            return false;
        }
        user.setVerified(true);
        userRep.save(user); // let's same user details

        // we don't need invalid password now
        secureTokenService.removeToken(secureToken);
        return true;
    }

    @Override
    public User getUserById(String id) throws UnknownIdentifierException {
        User user= userRep.findUserByEmail(id);
        if(user == null || BooleanUtils.isFalse(user.isVerified())){
            // we will ignore in case account is not verified or account does not exists
            throw new UnknownIdentifierException("unable to find account or account is not active");
        }
        return user;
    }

//    @Override
//    public MfaTokenData mfaSetup(String email) throws UnkownIdentifierException, QrGenerationException {
//        UserEntity user= userRepository.findByEmail(email);
//        if(user == null ){
//            // we will ignore in case account is not verified or account does not exists
//            throw new UnkownIdentifierException("unable to find account or account is not active");
//        }
//        return new MfaTokenData( mfaTokenManager.getQRCode( user.getSecret()), user.getSecret());
//    }

    private void encodePassword(UserDTOImpl source, User target){
        target.setPasswort(passwordEncoder.encode(source.getPassword()));
    }


}
