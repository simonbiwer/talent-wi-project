package com.example.application.utils;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.ArrayList;


public class Globals {


    public static final String CURRENT_USER = "current_User";

    public static class Pages {
        private Pages() {
            throw new IllegalStateException(ExceptionMessage.UTILITY);
        }
        public static final String APPLICATION_VIEW             = "apply/";
        public static final String CONTACTING_VIEW              = "contacting/";
        public static final String JOBADVERTISEMENT_VIEW        = "jobadvertisement/";
        public static final String JOBAPPLICATION_VIEW          = "jobapplication/";
        public static final String JOBLIST_VIEW                 = "joblist/";
        public static final String LOGIN_VIEW                   = "login/";
        public static final String MAIN_VIEW                    = "";
        public static final String MENU_VIEW                    = "menu/";
        public static final String PROFILE_VIEW                 = "profile/";            // Student
        public static final String PROFILE_EDIT_VIEW            = "profile_edit/";
        public static final String RECRUITMENT_VIEW             = "recruitment_formular/";
        public static final String REGISTER_VIEW                = "register/";
        public static final String SETTINGS_VIEW                = "settings/";
        public static final String VERIFY_VIEW                  = "register/verify";
    }



    public static class PageTitles {
        private PageTitles() {
            throw new IllegalStateException(ExceptionMessage.UTILITY);
        }
        public static final String APPLICATION_PAGE_TITLE       = "Application";
        public static final String JOBADVERTISEMENT_PAGE_TITLE  = "Stellenangebot";
        public static final String JOBLIST_PAGE_TITLE           = "Liste der Stellenangebote";
        public static final String LOGIN_PAGE_TITLE             = "Login";
        public static final String MAIN_PAGE_TITLE              = "Main";
        public static final String REGISTER_PAGE_TITLE          = "Registration";
        public static final String VERIFY_PAGE_TITLE            = "Verifizierung";
    }

    public static class ExceptionMessage {
        private ExceptionMessage() {
            throw new IllegalStateException(ExceptionMessage.UTILITY);
        }
        public static final String UTILITY = "Utility Class";
    }


    public static class Countries {
        private Countries() {
            throw new IllegalStateException(ExceptionMessage.UTILITY);
        }

        public static List<String> getCountries() {
            List<String> countryNames = new ArrayList<>();
            for (String countryCode : Locale.getISOCountries()) {
                Locale obj = new Locale("de", countryCode);
                countryNames.add(obj.getDisplayCountry(Locale.GERMAN));
            }
            Collections.sort(countryNames);
            return countryNames;
        }
    }

    public static class View {
        private View() {
            throw new IllegalStateException(ExceptionMessage.UTILITY);
        }
        public static final String BACKGROUND_COLOR = "background-color";
        public static final String ERROR = "Fehler";
        public static final String POSTAL_CODE = "Bitte geben Sie eine Postleitzahl ein";

    }


}
