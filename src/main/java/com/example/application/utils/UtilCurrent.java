package com.example.application.utils;

import com.example.application.dtos.impl.UserDTOImpl;
import com.example.application.entities.User;
import com.vaadin.flow.component.UI;


public class UtilCurrent {// Getters

    private UtilCurrent() {
        throw new IllegalStateException("Utility Class");
    }

    public static String getCurrentLocation() {
        return UI.getCurrent().getInternals().getActiveViewLocation().getPath();
    }

    public static User getCurrentUser() {
        return (User) UI.getCurrent().getSession().getAttribute(Globals.CURRENT_USER);
    }
}
