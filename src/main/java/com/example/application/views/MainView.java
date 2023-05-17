package com.example.application.views;

import com.example.application.controls.JobControl;
import com.example.application.dtos.KeywordDTO;
import com.example.application.dtos.StellenanzeigenDTO;
import com.example.application.dtos.impl.StellenanzeigenDTOImpl;
import com.example.application.entities.Keyword;
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
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Startseite beim Aufruf der Seite bzw. starten der Applikation.
 * last edited: ho 05.05.23
 */

@Route(value = Globals.Pages.MAIN_VIEW, layout = DefaultView.class)
@PageTitle(Globals.PageTitles.MAIN_PAGE_TITLE)
public class MainView extends VerticalLayout {

    @Autowired
    private JobControl jobControl;

    private Button registerButton;
    private Button logoutButton;

    private Button addJobButton;

    public MainView() {

        registerButton = new Button("Registrieren");
        logoutButton = new Button("Logout");
        addJobButton = new Button("Job hinzufügen");
        registerButton.addClassName("default-btn");
        logoutButton.addClassName("default-btn");
        addJobButton.addClassName("default-btn");

        setWidthFull();
        setHeightFull();

        registerButton.addClickListener(e -> {
            UtilNavigation.navigateToRegistration();
//            List<StellenanzeigenDTO> jobs = jobControl.readAllStellenanzeigen();
//            StellenanzeigenDTO job = jobs.get(0);
//            job.setBeschreibung("Doch die alte Beschreibung");
//            job.setProjektdauer("2 Monate");
//            jobControl.updateStellenanzeige(job);

        });

        logoutButton.addClickListener(e -> {
            UI.getCurrent().getSession().setAttribute(Globals.CURRENT_USER, null);
            UtilNavigation.navigateToLogin();
        });
        logoutButton.addClickShortcut(Key.ENTER);

        addJobButton.addClickListener(e -> {
            UtilNavigation.navigateToAddFormular();
        });

        setMargin(true);
        setHorizontalComponentAlignment(Alignment.CENTER, logoutButton, registerButton);
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        add(logoutButton);
        add(registerButton);
        add(addJobButton);
    }

    //Methode um den View zu beenden, falls der Nutzer nicht eingeloggt ist
    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        if (!(UI.getCurrent().getSession().getAttribute(Globals.CURRENT_USER) instanceof User)) {
            UtilNavigation.navigateToLogin();
        }
    }

}
