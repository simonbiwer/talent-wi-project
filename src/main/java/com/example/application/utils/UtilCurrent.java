package com.example.application.utils;

import com.example.application.dtos.UserDTOImpl;
import com.vaadin.flow.component.UI;


public class UtilCurrent {// Getters

    private UtilCurrent() {
        throw new IllegalStateException("Utility Class");
    }

    public static String getCurrentLocation() {
        return UI.getCurrent().getInternals().getActiveViewLocation().getPath();
    }

    public static UserDTOImpl getCurrentUser() {
        return (UserDTOImpl) UI.getCurrent().getSession().getAttribute(Globals.CURRENT_USER);
    }
}
