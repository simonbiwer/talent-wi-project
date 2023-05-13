package com.example.application.utils;

import com.vaadin.flow.component.UI;

import java.util.Objects;
public class UtilNavigation {

    private UtilNavigation() {
        throw new IllegalStateException("Utility Class");
    }



    // Navigation Methods
    public static void navigateToRegistration() {
        if (!Objects.equals(UtilCurrent.getCurrentLocation(), Globals.Pages.REGISTER_VIEW))
            UI.getCurrent().navigate(Globals.Pages.REGISTER_VIEW);
    }

    public static void navigateToVerification(){
        UI.getCurrent().navigate(Globals.Pages.VERIFY_VIEW);
    }

    public static void navigateToMain() {
        if (!Objects.equals(UtilCurrent.getCurrentLocation(), Globals.Pages.MAIN_VIEW))
            UI.getCurrent().navigate(Globals.Pages.MAIN_VIEW);
    }

    public static void navigateToLogin() {
        if (!Objects.equals(UtilCurrent.getCurrentLocation(), Globals.Pages.LOGIN_VIEW))
            UI.getCurrent().navigate(Globals.Pages.LOGIN_VIEW);
    }

    public static void navigateToAddFormular() {
        if (!Objects.equals(UtilCurrent.getCurrentLocation(), Globals.Pages.JOBADD_VIEW))
            UI.getCurrent().navigate(Globals.Pages.JOBADD_VIEW);
    }

    public static void navigateToJobList() {
        if (!Objects.equals(UtilCurrent.getCurrentLocation(), Globals.Pages.JOBLIST_VIEW))
            UI.getCurrent().navigate(Globals.Pages.JOBLIST_VIEW);
    }

    public static void navigateToJobList(String keyword) {
        if (!Objects.equals(UtilCurrent.getCurrentLocation(), Globals.Pages.JOBLIST_VIEW))
            UI.getCurrent().navigate(Globals.Pages.JOBLIST_VIEW + keyword);
    }

    public static void navigateToJobList(String keyword, String type) {
        if (!Objects.equals(UtilCurrent.getCurrentLocation(), Globals.Pages.JOBLIST_VIEW))
            UI.getCurrent().navigate(Globals.Pages.JOBLIST_VIEW + keyword + "/" + type);
    }

    public static void navigateToSettings() {
        if (!Objects.equals(UtilCurrent.getCurrentLocation(), Globals.Pages.SETTINGS_VIEW))
            UI.getCurrent().navigate(Globals.Pages.SETTINGS_VIEW);
    }

}
