package com.example.application.utils;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

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

}
