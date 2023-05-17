package com.example.application.layout;

import com.example.application.utils.UtilNavigation;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.html.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@CssImport(value = "./styles/layout-style.css")
public class DefaultView extends AppLayout {

    public DefaultView() {

        HorizontalLayout header = new HorizontalLayout();
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);

        //HomeButton wird erzeugt
        Image logo = new Image("/icons/logo_talent_pic_text.png", "Logo");
        logo.setHeight(("45px"));
        Button homeButton = new Button(logo);
        homeButton.getStyle().set("background", "transparent");
        homeButton.addClickListener(e -> {
            UtilNavigation.navigateToMain();
        });
        header.add(homeButton);

        header.setHeight("5em");

        HorizontalLayout filler = new HorizontalLayout();
        filler.setWidth("100%");

        Tab main = new Tab(VaadinIcon.TABLE.create(), new Span("Projektübersicht"));
        Tab addView = new Tab(VaadinIcon.INSERT.create(), new Span("Projekt hinzufügen"));
        Tab profile = new Tab(VaadinIcon.USER.create(), new Span("Profil"));
        Tab settings = new Tab(VaadinIcon.COG.create(), new Span("Settings"));

        Tabs tabs = new Tabs(main, addView, profile, settings);
        tabs.setOrientation(Tabs.Orientation.VERTICAL);

        tabs.addSelectedChangeListener(event -> {
            Tab selectedTab = event.getSelectedTab();
            if(selectedTab.equals(main)){
                UtilNavigation.navigateToMain();
            }else if(selectedTab.equals(addView)){
                UtilNavigation.navigateToAddFormular();
            }else if(selectedTab.equals(profile)){
                UtilNavigation.navigateToProfile();
            }else if(selectedTab.equals(settings)){
                UtilNavigation.navigateToSettings();
            }
        });

        DrawerToggle toggle = new DrawerToggle();
        toggle.setClassName("toggle");
        addToDrawer(tabs);


        addToNavbar(toggle, header);
        //setPrimarySection(Section.DRAWER);    Styling to be decided
    }

}
