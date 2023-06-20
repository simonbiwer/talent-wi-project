package com.example.application.utils;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.UrlValidator;

/**
 * @author tb
 * @lastEdited 31.05.23
 * Klasse, die Utility-Methoden enthält zum Checken von Textfeldern
 */


public class Utils {
    private Utils() {
        throw new IllegalStateException("Utility Class");
    }

    public static void triggerDialogMessage(String headerText, String message) {
        Dialog dialog = new Dialog();

        H3 header = new H3(headerText);
        Label contentText = new Label(message);

        Button ok = new Button("Ok");

        ok.addClickListener(e -> dialog.close());

        HorizontalLayout text = new HorizontalLayout(contentText);
        HorizontalLayout butt = new HorizontalLayout(ok);

        VerticalLayout dialogContent = new VerticalLayout(header, text, butt);
        dialogContent.setAlignItems(FlexComponent.Alignment.CENTER);
        dialog.add(dialogContent);
        dialog.open();
    }

    public static boolean isAlpha(String text) {
        if(text.length()==0){return false;}
        for (char c : text.toCharArray()) {
            // a - z                    // A - Z                       // ö, ü, ä, ß
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == 'ö' || c == 'ß' || c == 'ä' || c == 'ü' )
                continue;
            return false;
        }
        return true;
    }

    public static boolean isNumber(String text){
        return StringUtils.isNumeric(text);
    }

    public static boolean hasNumber(String text){
        for(char c : text.toCharArray())
            if( Character.isDigit(c) ) return true;

        return false;
    }

    public static boolean hasUpperCaseLetter(String text) {
        for (char c : text.toCharArray()) {
            // A - Z
            if (c >= 'A' && c <= 'Z')
                return true;
            // Ö, Ü, Ä
            if (c == 'Ö' || c == 'Ä' || c == 'Ü')
                return true;
        }
        return false;
    }

    public static boolean passwortCheck(String passwort){
        boolean longEnough = passwort.length() > 7;
        boolean hasNumber = Utils.hasNumber(passwort);
        boolean hasUppercaseLetter = Utils.hasUpperCaseLetter(passwort);

        return longEnough && hasNumber && hasUppercaseLetter;
    }

    public static boolean emailadresseCheck(String emailadresse) {
        boolean retValue = true;
        int i = emailadresse.indexOf("@");
        int j = emailadresse.indexOf(".", i);

        if (i == 0) { // Anzahl der Zeichen vor dem @
            retValue = false;
        }

        if (j == -1)  { // Prüft ob kein Punkt nach dem @ Zeichen kommt
            retValue = false;
        }
        if ((j - i) < 2)  { // Prüft Anzahl der Zeichen zwischen dem @ und dem .
            retValue = false;
        }
        if (j == (emailadresse.length()-1)) { // Mail Adresse muss länger sein, als die Stelle vom Punkt
            retValue = false;
        }
        return retValue;
    }

    public static String  capitalizeString(String phrase){
        return phrase.substring(0, 1).toUpperCase()+phrase.substring(1);
    }

    public static boolean isURLValid(String urlString) {
        UrlValidator urlValidator = new UrlValidator();
        return urlValidator.isValid(urlString);

    }
}
