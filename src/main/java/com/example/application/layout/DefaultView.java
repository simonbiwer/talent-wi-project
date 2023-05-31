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

/**
 * Basis-Layout mit Navbar für die Webseite
 * @author hh 10.05.23
 * @since 31.05.23
 */
@CssImport(value = "./styles/layout-style.css")
public class DefaultView extends AppLayout {

    private Tab job;
    private Tab main;
    private Tabs tabs;
    public DefaultView() {

        HorizontalLayout header = new HorizontalLayout();
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);

        //HomeButton wird erzeugt
        Image logo = new Image("/icons/logo_talent_pic_text.png", "Logo");
        logo.setHeight(("45px"));
        Button homeButton = new Button(logo);
        homeButton.addClickListener(e -> {
            UtilNavigation.navigateToMain();
        });
        homeButton.getStyle().set("background", "transparent");

        header.add(homeButton);

        header.setHeight("5em");

        HorizontalLayout filler = new HorizontalLayout();
        filler.setWidth("100%");

        job = new Tab(VaadinIcon.TABLE.create(), new Span("Job"));
        job.setVisible(false);
        main = new Tab(VaadinIcon.TABLE.create(), new Span("Projektübersicht"));
        Tab addView = new Tab(VaadinIcon.INSERT.create(), new Span("Projekt hinzufügen"));
        Tab profile = new Tab(VaadinIcon.USER.create(), new Span("Profil"));
        Tab settings = new Tab(VaadinIcon.COG.create(), new Span("Settings"));


        tabs = new Tabs(job, main, addView, profile, settings);
        tabs.setSelectedTab(main);
        tabs.setOrientation(Tabs.Orientation.VERTICAL);



        tabs.addSelectedChangeListener(event -> {
            Tab selectedTab = event.getSelectedTab();
            if(selectedTab.equals(main)){
                job.setVisible(false);
                UtilNavigation.navigateToMain();
            }else if(selectedTab.equals(addView)){
                job.setVisible(false);
                UtilNavigation.navigateToAddFormular();
            }else if(selectedTab.equals(profile)){
                job.setVisible(false);
                UtilNavigation.navigateToProfile();
            }else if(selectedTab.equals(settings)){
                job.setVisible(false);
                UtilNavigation.navigateToSettings();
            }
        });

        DrawerToggle toggle = new DrawerToggle();
        toggle.setClassName("toggle");
        addToDrawer(tabs);


        addToNavbar(toggle, header);
        //setPrimarySection(Section.DRAWER);    Styling to be decided
    }

    /**
     * Methode, die bei der Navigierung der Tabelle zu einem Job, dan zugehörigen Tab dynamisch hinzufügt
     * @param title Titel der Stellenanzeige
     */
    public void navigateToJob(String title){
        job.setLabel(title);
        job.setVisible(true);
        tabs.setSelectedTab(job);
    }

    public void removeUserTab(){
        job.setVisible(false);
        tabs.setSelectedTab(main);
    }
}
