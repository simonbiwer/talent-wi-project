package com.example.application.views.main;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Main")
@Route(value = "")
public class MainView extends VerticalLayout {

    private TextField name;
    private PasswordField password;
    private Button login;

    public MainView() {
        name = new TextField("Your name");
        password = new PasswordField("Your password", "Enter Password here");
        login = new Button("Login");

        setWidthFull();
        setHeightFull();

        login.addClickListener(e -> {
            Notification notification = Notification.show(name.getValue()+" is now logged in");
            notification.setPosition(Notification.Position.MIDDLE);
            name.setValue("");
            password.setValue("");
        });
        login.addClickShortcut(Key.ENTER);

        setMargin(true);
        setHorizontalComponentAlignment(Alignment.CENTER, name, password, login);
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        add(name, password, login);
    }

}
