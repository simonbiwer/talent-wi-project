package com.example.application.views;

import com.example.application.controls.JobControl;
import com.example.application.dtos.StellenanzeigenDTO;
import com.example.application.entities.Stellenanzeige;
import com.example.application.entities.User;
import com.example.application.layout.DefaultView;
import com.example.application.utils.JobInjectService;
import com.example.application.utils.SettingsService;
import com.example.application.utils.UtilNavigation;
import com.example.application.utils.Globals;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.*;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;
import jakarta.persistence.Column;
import org.atmosphere.interceptor.AtmosphereResourceStateRecovery;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

/**
 * Startseite beim Aufruf der Seite bzw. starten der Applikation.
 *
 * @author hh
 * @since 24.05.2023
 */

@Route(value = Globals.Pages.MAIN_VIEW, layout = DefaultView.class)
@PageTitle(Globals.PageTitles.MAIN_PAGE_TITLE)
public class MainView extends VerticalLayout {

    private DefaultView defaultView;

    @Autowired
    private JobControl jobControl;

    @Autowired
    private JobInjectService jobInjectService;

    @Autowired
    private SettingsService settingsService;

    Grid<StellenanzeigenDTO> grid;

    public MainView() {

        setWidthFull();
        setHeightFull();

        setMargin(true);
        setHorizontalComponentAlignment(Alignment.CENTER);
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        FilterFormLayout filter = new FilterFormLayout();
        filter.setVisible(false);
        filter.addClassName("filter-layout");

        HorizontalLayout headerBox = new HorizontalLayout();

        Button toggleFilter = new Button("Filter Anpassen", e -> {
            filter.setVisible(!filter.isVisible());
        });
        toggleFilter.addClassName("default-btn");

        headerBox.add(new Label("Verfügbar"), toggleFilter);
        headerBox.addClassName("custom-header");

        grid = new Grid<>(StellenanzeigenDTO.class, false);


        grid.addColumn(StellenanzeigenDTO::getTitel).setHeader("Titel");
        grid.addColumn(StellenanzeigenDTO::getUnternehmen).setHeader("Unternehmen");
        grid.addColumn(StellenanzeigenDTO::getStartdatum).setHeader("Startdatum");
        grid.addComponentColumn(stellenAnzeige -> createStatusIcon(!stellenAnzeige.getReserved())) //TODO: stellenAnzeige.getReserved()
                .setHeader(headerBox);



        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        grid.setSelectionMode(Grid.SelectionMode.NONE);

        grid.addItemClickListener(event -> {
            jobInjectService.setStellenanzeige(event.getItem());
            UtilNavigation.navigateToJobAdvertisement();
        });

        addClassName("remove-Vaadin");
        this.getStyle().set("gap", "0");
        add(filter, grid);
    }

    /**
     * Diese Klasse dient als Layout für den Filter und erstellt die Elemente
     */
    class FilterFormLayout extends HorizontalLayout{
        FilterFormLayout(){
            TextField filter1 = new TextField("test");
            Button applyFilterbtn = new Button("Filter anwenden", e->{
               //TODO
            });
            add(filter1, applyFilterbtn);
        }
    }


    /**
     * Diese Methode verhindert dass ein Nutzer, der nicht eingeloggt ist, diese View sehen kann
     * Außerdem werden hier mittels der JobControl, alle Stellenanzeigen aus der Datenbank ausgelesen und in die Tabelle eingefügt
     *
     * @param attachEvent Das Event-Objekt.
     */
    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        if (!(UI.getCurrent().getSession().getAttribute(Globals.CURRENT_USER) instanceof User)) {
            UtilNavigation.navigateToLogin();
        }else{
            defaultView = (DefaultView) getParent().get();
            defaultView.removeUserTab();
        }
        //Hinzufügen der Daten in der Tabelle
        settingsService.setJobHinzufuegen(false);
        List<StellenanzeigenDTO> jobs = jobControl.readAllStellenanzeigen();
        grid.setItems(jobs);
    }

    /**
     * Diese Hilfs-Methode erzeugt die Icons, die Anzeige ob eine Stelle belegt ist
     *
     * @param status boolscher Wert, der bestimmt ob die Stellenanzeige bereits belegt ist.
     * @return gibt das Icon zurück, welches dann angezeigt wird
     */
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
