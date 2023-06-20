package com.example.application.views;

import com.example.application.controls.UserControl;
import com.example.application.entities.User;
import com.example.application.layout.DefaultView;
import com.example.application.utils.Globals;
import com.example.application.utils.UtilNavigation;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * View für die Einstellungen
 */

@CssImport(value = "./styles/layout-style.css")
@Route(value = Globals.Pages.SETTINGS_VIEW, layout = DefaultView.class)
@PageTitle(Globals.PageTitles.SETTINGS_PAGE_TITLE)
public class SettingsView extends VerticalLayout {
    @Autowired
    private UserControl userControl;

    class SettingsForm extends Div {

    }


    public SettingsView() {
        VerticalLayout section = new VerticalLayout();
        SettingsForm form = new SettingsForm();
        setWidthFull();
        setHeightFull();

        Dialog dialog = new Dialog();

        dialog.setHeaderTitle("Nutzeraccount löschen");

        dialog.add("Sind sie sicher, dass sie ihren Account permanent löchen wollen?");

        Button abortModalButton = new Button("Abbrechen", e -> dialog.close());
        abortModalButton.getStyle().set("margin-right", "auto");
        dialog.getFooter().add(abortModalButton);
        Button deleteModalButton = new Button("Fortfahren", e -> {
            User user = (User) UI.getCurrent().getSession().getAttribute(Globals.CURRENT_USER);
            userControl.deleteUser(user.getUserid());
            dialog.close();
            UtilNavigation.navigateToMain();
        });
        dialog.getFooter().add(deleteModalButton);
        deleteModalButton.addClassName("delete-btn");

        Button deletebutton = new Button("Account löschen");
        deletebutton.addClickListener(event -> {
            dialog.open();
        });
        deletebutton.addClassName("delete-btn");

        section.add(deletebutton);
        add(section);
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        if (!(UI.getCurrent().getSession().getAttribute(Globals.CURRENT_USER) instanceof User)) {
            UtilNavigation.navigateToLogin();
        }
    }
}
