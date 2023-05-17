package com.example.application.views;

import com.example.application.entities.User;
import com.example.application.layout.DefaultView;
import com.example.application.utils.Globals;
import com.example.application.utils.UtilNavigation;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@CssImport(value = "./styles/layout-style.css")
@Route(value = Globals.Pages.SETTINGS_VIEW, layout = DefaultView.class)
@PageTitle(Globals.PageTitles.SETTINGS_PAGE_TITLE)
public class SettingsView extends VerticalLayout {


    public SettingsView(){
        setWidthFull();
        setHeightFull();
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        if (!(UI.getCurrent().getSession().getAttribute(Globals.CURRENT_USER) instanceof User)) {
            UtilNavigation.navigateToLogin();
        }
    }
}
