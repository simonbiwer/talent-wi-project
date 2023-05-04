package com.example.application.views;

import com.example.application.entities.User;
import com.example.application.layout.DefaultView;
import com.example.application.utils.UtilNavigation;
import com.example.application.utils.Globals;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.UI;

/**
 * Startseite beim Aufruf der Seite bzw. starten der Applikation.
 */

@Route(value = Globals.Pages.MAIN_VIEW, layout = DefaultView.class)
@PageTitle(Globals.PageTitles.MAIN_PAGE_TITLE)
public class MainView extends VerticalLayout {

    private Button registerButton;
    private Button logoutButton;
    public MainView() {

        registerButton = new Button("Registrieren");
        logoutButton = new Button("Logout");
        setWidthFull();
        setHeightFull();

        registerButton.addClickListener(e -> {
            UtilNavigation.navigateToRegistration();
        });

        logoutButton.addClickListener(e -> {
            UI.getCurrent().getSession().setAttribute( Globals.CURRENT_USER, null );
            UtilNavigation.navigateToLogin();
        });
        logoutButton.addClickShortcut(Key.ENTER);

        setMargin(true);
        setHorizontalComponentAlignment(Alignment.CENTER, logoutButton, registerButton);
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        add(logoutButton);
        add(registerButton);
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        if(!(UI.getCurrent().getSession().getAttribute( Globals.CURRENT_USER) instanceof User)){
            UtilNavigation.navigateToLogin();
        }
    }

}
