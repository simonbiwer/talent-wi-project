package com.example.application.views;

import com.example.application.dtos.LoginResultDTO;
import com.example.application.dtos.UserDTO;
import com.example.application.entities.User;
import com.example.application.layout.DefaultView;
import com.example.application.utils.Globals;
import com.example.application.utils.UtilNavigation;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.controls.LoginControl;
import com.vaadin.flow.router.RouterLink;
import org.springframework.beans.factory.annotation.Autowired;
import com.vaadin.flow.component.UI;

/**
 * Login-View zur Anzeige der Startseite und des Login-Formulars.
 */

@Route(value = Globals.Pages.LOGIN_VIEW, layout = DefaultView.class)
@PageTitle(Globals.PageTitles.LOGIN_PAGE_TITLE)
public class LoginView extends VerticalLayout {

    @Autowired
    private LoginControl loginControl;

    public LoginView() {
        setSizeFull();
        LoginForm component = new LoginForm();

        addClassName("login-view");

        component.addLoginListener(e -> {
            LoginResultDTO isAuthenticated = loginControl.authentificate( e.getUsername() , e.getPassword() );

            if (isAuthenticated.getResult()) {
                grabAndSetUserIntoSession();
                navigateToMainPage();
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

    private void navigateToMainPage() {
        UtilNavigation.navigateToMain();
    }

}
