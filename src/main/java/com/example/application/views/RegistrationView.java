package com.example.application.views;

import com.example.application.controls.RegistrationControl;
import com.example.application.dtos.RegistrationResult;
import com.example.application.dtos.UserDTOImpl;
import com.example.application.layout.DefaultView;
import com.example.application.utils.Globals;
import com.example.application.utils.Utils;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * View für die Registrierung
 * last edited: sb 01.05.23
 */

@Route(value = "register", layout = DefaultView.class)
@PageTitle(Globals.PageTitles.REGISTER_PAGE_TITLE)
public class RegistrationView extends Div{


    @Autowired
    private RegistrationControl registrationControl;
    /*
    @Autowired
    private LoginControl loginControl;
    @Autowired
    private SettingsControl settingsControl;
     */

    // Basic User
    EmailField email = new EmailField("E-Mail");
    TextField firstname = new TextField("Vorname");
    TextField lastname = new TextField("Nachname");
    PasswordField password  = new PasswordField("Password");
    PasswordField password2 = new PasswordField("Passwort bestätigen");

    private Binder<UserDTOImpl> binderUser = new Binder(UserDTOImpl.class);


    class RegisterForm extends Div {

        RegisterForm(){
            firstname.setRequiredIndicatorVisible(true);
            lastname.setRequiredIndicatorVisible(true);
            email.setRequiredIndicatorVisible(true);
            email.setClearButtonVisible(false); // Deaktiviert die Validierung des EmailFields
            email.setErrorMessage("E-Mail ungültig"); // Setzt die benutzerdefinierte Fehlermeldung für ungültige Eingaben
            password.setRequiredIndicatorVisible(true);
            password2.setRequiredIndicatorVisible(true);

            FormLayout formLayout = new FormLayout();
            formLayout.add(
                    firstname, lastname,
                    email,password, password2
            );
            // Stretch country textfield to full row width
//            formLayout.setColspan(country, 2);
            formLayout.setSizeUndefined();
            this.add(formLayout);
        }
    }

    public RegistrationView() {

        VerticalLayout section = new VerticalLayout();
        section.setWidth("50%");

        H1 h1 = new H1("Registrierung");


        RegisterForm form = new RegisterForm();
        form.getElement().getStyle().set("Margin", "30px");
        Button registerButton = new Button("Registrieren");
        section.add(h1, form, registerButton);

        section.setPadding(true);
        section.setAlignItems(FlexComponent.Alignment.CENTER);
        section.setAlignSelf(FlexComponent.Alignment.CENTER);

        HorizontalLayout siteLayout = new HorizontalLayout();
        siteLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        siteLayout.setAlignSelf(FlexComponent.Alignment.CENTER);
        siteLayout.setSizeFull();
        siteLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        siteLayout.add(section);

        // Bindings initialisieren
        binderUser.setBean(new UserDTOImpl());
        binderUser.forField(firstname)
                .withValidator(bindervorname -> bindervorname.length() > 0, "Bitte Vornamen angeben!")
                .withValidator(Utils::isAlpha, "Vorname darf nur Buchstaben enthalten")
                .bind(UserDTOImpl::getFirstname, UserDTOImpl::setFirstname);
        binderUser.forField(lastname)
                .withValidator(bindernachname -> bindernachname.length() > 0, "Bitte Nachnamen angeben!")
                .withValidator(Utils::isAlpha, "Nachname darf nur Buchstaben enthalten")
                .bind(UserDTOImpl::getLastname, UserDTOImpl::setLastname);
        binderUser.forField(email)
                .withValidator(binderemailadresse -> binderemailadresse.length() > 0, "Bitte E-Mailadresse angeben!")
                .bind(UserDTOImpl::getEmail, UserDTOImpl::setEmail);
        binderUser.forField(password)
                .withValidator(binderpassword -> binderpassword.length() > 0, "Bitte Passwort angeben!")
                .withValidator(Utils::passwortCheck, "Mind. 8 Zeichen, davon mind. eine Zahl und ein Großbuchstabe")
                .bind(UserDTOImpl::getPassword,UserDTOImpl::setPassword);
        binderUser.forField(password2)
                .withValidator(binderpasswortwiederholen -> binderpasswortwiederholen.length() > 0, "Bitte Passwort angeben!")
                .withValidator(binderpasswortwiederholen -> binderpasswortwiederholen.equals(password.getValue()), "Passwörter stimmen nicht überein!")
                .bind(UserDTOImpl::getPassword, UserDTOImpl::setPassword);

        //Email-Adresse
        email.setPrefixComponent(VaadinIcon.GLOBE.create());
        email.setClearButtonVisible(true);
        email.setRequiredIndicatorVisible(true);

        //Passwort
        Icon checkIcon = VaadinIcon.CHECK.create();
        checkIcon.setVisible(false);
        password.setSuffixComponent(checkIcon);


        registerButton.addClickListener(e -> {
            if (email.isInvalid()){
                Notification.show("Bitte geben Sie eine gültige E-Mail Adresse ein!");
            } else if (email.isEmpty() || password.isEmpty()){
                Notification.show("Bitte alle Pflichtfelder ausfüllen!");
            } else if (!password.getValue().equals(password2.getValue())){
                Notification.show("Passwörter stimmen nicht überein!");
            } else {
                UserDTOImpl userDTOImpl = binderUser.getBean();
                try{
                    RegistrationResult result = registrationControl.registerUser(userDTOImpl);
                    if (result.OK()){
                        Notification.show("Registrierung erfolgreich");
                        UI.getCurrent().navigate(Globals.Pages.LOGIN_VIEW);
                        // Eventuell automatischer Login hinzufügen
                    }
                    Notification.show(result.getMessage());
                } catch (Exception exception){
                    Notification.show("Registrierung fehlgeschlagen");
                }
            }
        });
        add(siteLayout);
    }

}
