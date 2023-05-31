package com.example.application.utils;

import com.example.application.dtos.StellenanzeigenDTO;
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

    public static void navigateToJobAdvertisement() {
        if (!Objects.equals(UtilCurrent.getCurrentLocation(), Globals.Pages.JOBADVERTISEMENT_VIEW))
            UI.getCurrent().navigate(Globals.Pages.JOBADVERTISEMENT_VIEW);
    }

    public static void navigateToJobAdvertisementEdit() {
        if (!Objects.equals(UtilCurrent.getCurrentLocation(), Globals.Pages.JOBADVERTISEMENT_EDIT_VIEW))
            UI.getCurrent().navigate(Globals.Pages.JOBADVERTISEMENT_EDIT_VIEW);
    }

    public static void navigateToSettings() {
        if (!Objects.equals(UtilCurrent.getCurrentLocation(), Globals.Pages.SETTINGS_VIEW))
            UI.getCurrent().navigate(Globals.Pages.SETTINGS_VIEW);
    }

    public static void navigateToProfile() {
        if (!Objects.equals(UtilCurrent.getCurrentLocation(), Globals.Pages.PROFILE_VIEW))
            UI.getCurrent().navigate(Globals.Pages.PROFILE_VIEW);
    }

}
