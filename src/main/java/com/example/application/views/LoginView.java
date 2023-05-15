package com.example.application.views;

import com.example.application.dtos.LoginResultDTO;
import com.example.application.entities.User;
import com.example.application.utils.Globals;
import com.example.application.utils.UtilNavigation;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.controls.LoginControl;
import com.vaadin.flow.router.RouterLink;
import org.springframework.beans.factory.annotation.Autowired;
import com.vaadin.flow.component.UI;

/**
 * Login-View zur Anzeige der Startseite und des Login-Formulars.
 * last edited: ho 05.05.23
 */
@CssImport(value = "./styles/layout-style.css")
@Route(value = Globals.Pages.LOGIN_VIEW)
@PageTitle(Globals.PageTitles.LOGIN_PAGE_TITLE)
public class LoginView extends VerticalLayout {

    @Autowired
    private LoginControl loginControl;

    EmailField email = new EmailField("E-Mail");
    PasswordField password = new PasswordField("Password");

    class LoginForm extends Div {

        LoginForm() {
            email.setRequiredIndicatorVisible(true);
            email.setClearButtonVisible(false); // Deaktiviert die Validierung des EmailFields
            email.setErrorMessage("E-Mail ungültig"); // Setzt die benutzerdefinierte Fehlermeldung für ungültige Eingaben
            password.setRequiredIndicatorVisible(true);

            FormLayout formLayout = new FormLayout();
            formLayout.add(
                    email, password
            );
            // Stretch country textfield to full row width
//            formLayout.setColspan(country, 2);
            formLayout.setSizeUndefined();
            this.add(formLayout);
        }
    }

    public LoginView() {

        this.addClassName("page-view");
        setSizeFull();

        VerticalLayout section = new VerticalLayout();
        section.setWidth("50%");

        HorizontalLayout company = new HorizontalLayout();

        Image logo = new Image("/icons/logo_talent_pic_text.png", "Logo");
        logo.setHeight("75px");

        H1 heading = new H1();
        heading.addClassName("project-title");

        company.add(logo);
        company.add(heading);

        company.addClassName("company-logo");
        company.setAlignItems(Alignment.CENTER);

        this.add(company);

        H3 h3 = new H3("Log In");

        LoginView.LoginForm form = new LoginView.LoginForm();
        form.getElement().getStyle().set("Margin", "20px");
        Button loginButton = new Button("Log In");
        loginButton.addClassName("default-btn");
        section.add(h3, form, loginButton, new RouterLink("Sie haben noch kein Konto? Registrieren Sie sich hier!", RegistrationView.class));

        addClassName("login-view");

        section.setPadding(true);
        section.setAlignItems(FlexComponent.Alignment.CENTER);
        section.setAlignSelf(FlexComponent.Alignment.CENTER);

        HorizontalLayout siteLayout = new HorizontalLayout();
        siteLayout.setAlignSelf(FlexComponent.Alignment.CENTER);
        siteLayout.setSizeFull();
        siteLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        siteLayout.add(section);

        loginButton.addClickListener(e -> {
            if (email.isInvalid()) {
                Notification.show("Bitte geben Sie eine gültige E-Mail Adresse ein!");
            } else if (email.isEmpty() || password.isEmpty()) {
                Notification.show("Bitte alle Pflichtfelder ausfüllen!");
            }

            LoginResultDTO isAuthenticated = loginControl.authentificate(email.getValue(), password.getValue());

            if (isAuthenticated.getResult()) {
                grabAndSetUserIntoSession();
                UtilNavigation.navigateToMain();
            } else {
                Notification.show(isAuthenticated.getReason());
            }
        });
        loginButton.addClickShortcut(Key.ENTER);

        this.setAlignItems(Alignment.CENTER);

        add(siteLayout);
    }

    private void grabAndSetUserIntoSession() {
        User user = loginControl.getCurrentUser();
        UI.getCurrent().getSession().setAttribute(Globals.CURRENT_USER, user);
    }

}
