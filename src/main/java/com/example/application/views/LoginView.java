package com.example.application.views;

import com.example.application.dtos.LoginResultDTO;
import com.example.application.entities.User;
import com.example.application.utils.Globals;
import com.example.application.utils.UtilNavigation;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
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

    public LoginView() {
        setSizeFull();


        HorizontalLayout company = new HorizontalLayout();

        Image logo = new Image("/icons/logo_talent.png", "Logo");
        logo.setHeight("100px");

        H1 heading = new H1("talent");
        heading.addClassName("project-title");

        company.add(logo);
        company.add(heading);

        company.setAlignItems(Alignment.CENTER);

        add(company);

        LoginForm component = new LoginForm();

        addClassName("login-view");

        component.addLoginListener(e -> {
            LoginResultDTO isAuthenticated = loginControl.authentificate( e.getUsername() , e.getPassword() );

            if (isAuthenticated.getResult()) {
                grabAndSetUserIntoSession();
                UtilNavigation.navigateToMain();
            } else {
                component.setError(true);
            }
        });

        add(component);

        add(new RouterLink("Sie haben noch kein Konto? Registrieren Sie sich hier!", RegistrationView.class));
        this.setAlignItems( Alignment.CENTER );
    }

    private void grabAndSetUserIntoSession() {
        User user = loginControl.getCurrentUser();
        UI.getCurrent().getSession().setAttribute( Globals.CURRENT_USER, user );
    }

}
