package com.example.application.layout;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@CssImport(value = "./styles/layout-style.css")
public class DefaultView extends AppLayout {
    public DefaultView() {
        // create the header
        HorizontalLayout header = new HorizontalLayout();
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);

        // add a toggle button for the navbar
        DrawerToggle toggle = new DrawerToggle();
        header.add(toggle);

        // add a logo to the header
        Image logo = new Image("/icons/logo_talent.png", "Logo");
        logo.setHeight("50px");
        header.add(logo);

        H1 heading = new H1("talent");
        heading.addClassName("project-title");
        header.add(heading);

        // create the navbar
        HorizontalLayout navbar = new HorizontalLayout();

        // create the navigation buttons
        Button button1 = new Button("Button 1");
        button1.setWidth("100%");
        navbar.add(button1);

        Button button2 = new Button("Button 2");
        button2.setWidth("100%");
        navbar.add(button2);

        Button button3 = new Button("Button 3");
        button3.setWidth("100%");
        navbar.add(button3);


        // add the header and navbar to the layout
        addToNavbar(header, navbar);
    }
}
