package com.example.application.views;

import com.example.application.controls.RegistrationControl;
import com.example.application.utils.Globals;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.ElementType;
import java.net.Authenticator;

@Route(value = Globals.Pages.VERIFY_VIEW)
public class RegistrationVerifyView extends Div {

    @Autowired
    RegistrationControl registrationControl;


}
