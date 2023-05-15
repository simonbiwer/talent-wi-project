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
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.Route;


@CssImport(value = "./styles/layout-style.css")
public class DefaultView extends AppLayout {
    public DefaultView() {

        HorizontalLayout header = new HorizontalLayout();
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);

        DrawerToggle toggle = new DrawerToggle();
        header.add(toggle);

        Image logo = new Image("/icons/logo_talent_pic_text.png", "Logo");
        logo.setHeight("50px");
        header.add(logo);

        //H1 heading = new H1("talent");
        //heading.addClassName("project-header");
        //header.add(heading);

        HorizontalLayout navbar = new HorizontalLayout();
        navbar.setFlexGrow(2); // Make the navbar flexible
        /*
        Button button1 = new Button("Button 1");
        button1.setWidth("100%");
        navbar.add(button1);

        Button button2 = new Button("Button 2");
        button2.setWidth("100%");
        navbar.add(button2);

        Button button3 = new Button("Button 3");
        button3.setWidth("100%");
        navbar.add(button3);
*/
        // Icons für toolbar
        HorizontalLayout button1Layout = new HorizontalLayout();
        Icon icon1 = new Icon(VaadinIcon.INSERT);
        Label label1 = new Label("Projekt hinzufügen");
        button1Layout.add(icon1, label1);

        Button button1 = new Button(button1Layout);
        button1.getStyle().set("background", "transparent");
        button1.getStyle().set("width", "100%"); // Adjust the width of the button
        navbar.add(button1);

        HorizontalLayout button2Layout = new HorizontalLayout();
        Icon icon2 = new Icon(VaadinIcon.TABLE);
        Label label2 = new Label("Projektenübersicht");
        button2Layout.add(icon2, label2);

        Button button2 = new Button(button2Layout);
        button2.getStyle().set("background", "transparent");
        button2.getStyle().set("width", "100%"); // Adjust the width of the button
        navbar.add(button2);

        addToNavbar(header, navbar);
    }

}
