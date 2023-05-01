package com.example.application.views;

import com.example.application.layout.DefaultView;
import com.example.application.utils.UtilNavigation;
import com.example.application.utils.Globals;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 * Startseite beim Aufruf der Seite bzw. starten der Applikation.
 */

@PageTitle("Main")
@Route(value = Globals.Pages.MAIN_VIEW, layout = DefaultView.class)
public class MainView extends VerticalLayout {

    private Button registerButton;
    private Button loginButton;
    public MainView() {

        registerButton = new Button("Registrieren");
        setWidthFull();
        setHeightFull();

        registerButton.addClickListener(e -> {
            UtilNavigation.navigateToRegistration();
        });
        registerButton.addClickShortcut(Key.ENTER);

        setMargin(true);
        setHorizontalComponentAlignment(Alignment.CENTER, registerButton);
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        add(registerButton);
    }

}
