package com.example.application.views;

import com.example.application.controls.AddJobControl;
import com.example.application.dtos.StellenanzeigenDTO;
import com.example.application.entities.Stellenanzeige;
import com.example.application.entities.User;
import com.example.application.layout.DefaultView;
import com.example.application.utils.UtilNavigation;
import com.example.application.utils.Globals;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Startseite beim Aufruf der Seite bzw. starten der Applikation.
 * last edited: ho 05.05.23
 */

@Route(value = Globals.Pages.MAIN_VIEW, layout = DefaultView.class)
@PageTitle(Globals.PageTitles.MAIN_PAGE_TITLE)
public class MainView extends VerticalLayout {

    @Autowired
    private AddJobControl addJobControl;

    Grid<Stellenanzeige> grid;

    public MainView() {

        setWidthFull();
        setHeightFull();

        setMargin(true);
        setHorizontalComponentAlignment(Alignment.CENTER);
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        grid = new Grid<>(Stellenanzeige.class, false);
        grid.addColumn(Stellenanzeige::getTitel).setHeader("Titel");
        grid.addColumn(Stellenanzeige::getUrl).setHeader("Url");
        grid.addComponentColumn(stellenAnzeige -> createStatusIcon(true)) //TODO: stellenAnzeige.getReserved()
                .setHeader("Status");

        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        add(grid);
    }

    //Methode um den View zu beenden, falls der Nutzer nicht eingeloggt ist
    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        if (!(UI.getCurrent().getSession().getAttribute(Globals.CURRENT_USER) instanceof User)) {
            UtilNavigation.navigateToLogin();
        }
        //Hinzufügen der Daten in der Tabelle
        List<Stellenanzeige> jobs = addJobControl.getJobs();
        grid.setItems(jobs);
    }

    private Icon createStatusIcon(boolean status) {
        Icon icon;
        if (status) {
            icon = VaadinIcon.CHECK.create();
            icon.getElement().getThemeList().add("badge success");
        } else {
            icon = VaadinIcon.CLOSE_SMALL.create();
            icon.getElement().getThemeList().add("badge error");
        }
        icon.getStyle().set("padding", "var(--lumo-space-xs");
        return icon;
    }

}
