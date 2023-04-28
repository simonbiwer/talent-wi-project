package com.example.application.views;

import com.example.application.controls.RegistrationControl;
import com.example.application.dtos.RegistrationResult;
import com.example.application.utils.Globals;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
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
import com.example.application.dtos.UserDTO;
import com.example.application.layout.DefaultView;
import org.springframework.beans.factory.annotation.Autowired;

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
    TextField salutation            = new TextField("Anrede");
    TextField title                 = new TextField("Titel");
    EmailField email                = new EmailField("E-Mail");
    EmailField emailRepeat          = new EmailField("E-Mail wiederholen");
    TextField firstname             = new TextField("Vorname");
    TextField lastname              = new TextField("Nachname");
    PasswordField password          = new PasswordField("Password");
    PasswordField passwordRepeat    = new PasswordField("Password wiederholen");
    DatePicker dateOfBirth          = new DatePicker("Geburtsdatum");
    TextField   phone               = new TextField("Telefon");
    // Address
    TextField   street              = new TextField("Straße");
    TextField   housenumber         = new TextField("Hausnummer");
    TextField   postalcode          = new TextField("PLZ");
    TextField   city                = new TextField("Stadt");
    ComboBox<String>   country      = new ComboBox<>("Country");

    private Binder<UserDTO> binderUser = new Binder(UserDTO.class);


    class RegisterForm extends Div {

        RegisterForm(){
            // Set required fields option
            // Basic User
            salutation.setRequiredIndicatorVisible(true);
            title.setRequiredIndicatorVisible(true);
            firstname.setRequiredIndicatorVisible(true);
            lastname.setRequiredIndicatorVisible(true);
            dateOfBirth.setRequiredIndicatorVisible(true);
            phone.setRequiredIndicatorVisible(true);
            email.setRequiredIndicatorVisible(true);
            emailRepeat.setRequiredIndicatorVisible(true);
            password.setRequiredIndicatorVisible(true);
            passwordRepeat.setRequiredIndicatorVisible(true);
            street.setRequiredIndicatorVisible(true);
            housenumber.setRequiredIndicatorVisible(true);
            postalcode.setRequiredIndicatorVisible(true);
            city.setRequiredIndicatorVisible(true);
            country.setRequiredIndicatorVisible(true);

            // Set input length
            password.setMinLength(5);
            passwordRepeat.setMinLength(5);

            country.setItems(Globals.Countries.getCountries());

            FormLayout formLayout = new FormLayout();
            formLayout.add(
                    salutation, title,
                    firstname, lastname,
                    dateOfBirth, phone,
                    street, housenumber,
                    postalcode, city,
                    country,
                    email, emailRepeat,
                    password, passwordRepeat
            );
            // Stretch country textfield to full row width
            formLayout.setColspan(country, 2);
            formLayout.setSizeUndefined();
            this.add(formLayout);
        }

        public UserDTO createNewUserDTO() {
            UserDTO newUser = new UserDTO();
            newUser.setSalutation(salutation.getValue());
            newUser.setTitle(title.getValue());
            newUser.setFirstname(firstname.getValue());
            newUser.setLastname(lastname.getValue());
            newUser.setDateOfBirth(dateOfBirth.getValue());
            newUser.setPhone(phone.getValue());
            newUser.setEmail(email.getValue());
            newUser.setPassword(password.getValue());

            return newUser;
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
        binderUser.setBean(new UserDTO());
        binderUser.bind(firstname, "firstname");
        binderUser.bind(lastname, "lastname");

        registerButton.addClickListener(e -> {
            UserDTO userDTO = binderUser.getBean();
            try{
                RegistrationResult result = registrationControl.registerUser(userDTO);
                if (result.OK()){
                    Notification.show("Registrierung erfolgreich");
                }
            } catch (Exception exception){
                Notification.show("Registrierung fehlgeschlagen");
            }
            /*try {
                UserDTO userDTO = form.createNewUserDTO();
                RegistrationDTOImpl registrationDTO = new RegistrationDTOImpl(userDTO, emailRepeat.getValue(), passwordRepeat.getValue());

                RegistrationResultDTO registrationResult = registrationControl.registerUser(registrationDTO);

                if (registrationResult.getResult()) {
                    // Success meldung
                    Utils.triggerDialogMessage("Registrierung abgeschlossen", "Sie haben sich erfolgreich registriert");

                    //TODO: automatischer Login
                    //autoLoginAfterRegistration(userDTO);

                    // Routing auf main Seite
                    UtilNavigation.navigateToMain();
                } else {
                    List<ReasonType> reasons = registrationResult.getReasons();
                    if (reasons.contains(ReasonType.UNEXPECTED_ERROR))
                        Utils.triggerDialogMessage(Globals.View.ERROR, "Es ist ein unerwarteter Fehler aufgetreten");

                    // Fehlerbehandlung: Fehlerhafte TextFields mit Error Message versehen und auf invalid setzen
                    setUserErrorFields(reasons);
                    setCompanyErrorFields(reasons);

                }
            } catch (DatabaseUserException databaseUserException) {
                Utils.triggerDialogMessage(Globals.View.ERROR,"Während der Registrierung ist ein Fehler aufgetreten: " + databaseUserException.getErrorCode());
            } catch (Exception exception) {
                Utils.triggerDialogMessage(Globals.View.ERROR,"Während der Registrierung ist ein unerwarteter Fehler aufgetreten: " + exception);
            }*/
        });
        add(siteLayout);
    }

}
