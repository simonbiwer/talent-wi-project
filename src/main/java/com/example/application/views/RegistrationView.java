package com.example.application.views;

import com.example.application.controls.RegistrationControl;
import com.example.application.dtos.RegistrationResult;
import com.example.application.dtos.UserDTOImpl;
import com.example.application.utils.Globals;
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
import com.example.application.layout.DefaultView;
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
//    TextField salutation            = new TextField("Anrede");
//    TextField title                 = new TextField("Titel");
    EmailField email                = new EmailField("E-Mail");
//    EmailField emailRepeat          = new EmailField("E-Mail wiederholen");
    TextField firstname             = new TextField("Vorname");
    TextField lastname              = new TextField("Nachname");
    PasswordField password          = new PasswordField("Password");
    PasswordField password2         = new PasswordField("Passwort bestätigen");
//    DatePicker dateOfBirth          = new DatePicker("Geburtsdatum");
//    TextField   phone               = new TextField("Telefon");
    // Address
//    TextField   street              = new TextField("Straße");
//    TextField   housenumber         = new TextField("Hausnummer");
//    TextField   postalcode          = new TextField("PLZ");
//    TextField   city                = new TextField("Stadt");
//    ComboBox<String>   country      = new ComboBox<>("Country");

    private Binder<UserDTOImpl> binderUser = new Binder(UserDTOImpl.class);


    class RegisterForm extends Div {

        RegisterForm(){
            // Set required fields option
            // Basic User
//            salutation.setRequiredIndicatorVisible(true);
//            title.setRequiredIndicatorVisible(true);
            firstname.setRequiredIndicatorVisible(true);
            lastname.setRequiredIndicatorVisible(true);
//            dateOfBirth.setRequiredIndicatorVisible(true);
//            phone.setRequiredIndicatorVisible(true);
            email.setRequiredIndicatorVisible(true);
//            emailRepeat.setRequiredIndicatorVisible(true);
            password.setRequiredIndicatorVisible(true);
            password2.setRequiredIndicatorVisible(true);
//            street.setRequiredIndicatorVisible(true);
//            housenumber.setRequiredIndicatorVisible(true);
//            postalcode.setRequiredIndicatorVisible(true);
//            city.setRequiredIndicatorVisible(true);
//            country.setRequiredIndicatorVisible(true);

            // Set input length
            password.setMinLength(4);
//            passwordRepeat.setMinLength(5);

//            country.setItems(Globals.Countries.getCountries());

            FormLayout formLayout = new FormLayout();
            formLayout.add(
//                    salutation, title,
                    firstname, lastname,
//                    dateOfBirth, phone,
//                    street, housenumber,
//                    postalcode, city,
//                    country,
                    email,
//                    emailRepeat,
                    password, password2
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
        binderUser.bind(firstname, "firstname");
        binderUser.bind(lastname, "lastname");
        binderUser.bind(email, "email");
        binderUser.bind(password, "password");

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
