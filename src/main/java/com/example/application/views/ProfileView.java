package com.example.application.views;

import com.example.application.entities.User;
import com.example.application.layout.DefaultView;
import com.example.application.utils.Globals;
import com.example.application.utils.UtilNavigation;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 * View zum Bearbeiten des Profils
 */

@CssImport(value = "./styles/layout-style.css")
@Route(value = Globals.Pages.PROFILE_VIEW, layout = DefaultView.class)
@PageTitle(Globals.PageTitles.PROFILE_PAGE_TITLE)
public class ProfileView extends VerticalLayout {

    private Button logoutButton;
    public ProfileView(){
        logoutButton = new Button("Logout");
        logoutButton.addClassName("default-btn");

        setWidthFull();
        setHeightFull();

        logoutButton.addClickListener(e -> {
            UI.getCurrent().getSession().setAttribute(Globals.CURRENT_USER, null);
            UtilNavigation.navigateToLogin();
        });
        logoutButton.addClickShortcut(Key.ENTER);

        add(logoutButton);
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        if (!(UI.getCurrent().getSession().getAttribute(Globals.CURRENT_USER) instanceof User)) {
            UtilNavigation.navigateToLogin();
        }
    }
}
