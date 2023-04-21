package com.example.application.utils;

import com.vaadin.flow.component.UI;
import com.example.application.dtos.UserDTO;


public class UtilCurrent {// Getters

    private UtilCurrent() {
        throw new IllegalStateException("Utility Class");
    }

    public static String getCurrentLocation() {
        return UI.getCurrent().getInternals().getActiveViewLocation().getPath();
    }

    public static UserDTO getCurrentUser() {
        return (UserDTO) UI.getCurrent().getSession().getAttribute(Globals.CURRENT_USER);
    }
}
