package com.example.application.views;


import com.example.application.controls.JobControl;
import com.example.application.controls.LoginControl;
import com.example.application.dtos.KeywordDTO;
import com.example.application.dtos.StellenanzeigenDTO;
import com.example.application.entities.User;
import com.example.application.layout.DefaultView;
import com.example.application.utils.Globals;
import com.example.application.utils.InjectService;
import com.example.application.utils.UtilNavigation;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * Diese Klasse ist für die Anzeige der Daten eines Stellenangebotes zuständig
 * Sie verwendet den JobInjectService um an das benötigte StellenAnzeigeDTO für die Daten zu gelangen
 *
 * @author hh
 * @since 24.05.2023
 */
@Route(value = Globals.Pages.JOBADVERTISEMENT_VIEW, layout = DefaultView.class)
@PageTitle(Globals.PageTitles.JOBADVERTISEMENT_PAGE_TITLE)
public class JobAdvertisementView extends VerticalLayout {
    @Autowired
    private JobControl jobControl;

    @Autowired
    private InjectService jobInjectService;

    @Autowired
    private LoginControl loginControl;

    private DefaultView defaultView;

    private StellenanzeigenDTO stellenAnzeige;
    VerticalLayout url = new VerticalLayout();
    VerticalLayout company = new VerticalLayout();
    VerticalLayout technology = new VerticalLayout();
    VerticalLayout qualifications = new VerticalLayout();
    VerticalLayout start = new VerticalLayout();
    VerticalLayout range = new VerticalLayout();
    VerticalLayout des = new VerticalLayout();
    private List<KeywordDTO> keywords;
    private HorizontalLayout cards;
    private VerticalLayout section;
    private Button contactButton;
    class ShowJobForm extends Div {
        ShowJobForm() {

            Anchor urlAnchor = new Anchor(stellenAnzeige.getUrl(), stellenAnzeige.getUrl());
            urlAnchor.getStyle().set("line-break", "anywhere");
            urlAnchor.getStyle().set("color", "black");
            urlAnchor.setTarget("_blank");

            //Werte werden eingefügt in die View
            url.add(new H4("URL"), urlAnchor);
            company.add(new H4("Unternehmen"), new Span(stellenAnzeige.getUnternehmen()!=null? stellenAnzeige.getUnternehmen().replace("'", ""): "Keine Angabe"));
            technology.add(new H4("Technologien"), new Span(stellenAnzeige.getTechnologien()!=null? stellenAnzeige.getTechnologien().replace("'", ""): "Keine Angabe"));
            qualifications.add(new H4("Qualifikationen"), new Span(stellenAnzeige.getQualifikation()!=null? stellenAnzeige.getQualifikation().replace("'", ""): "Keine Angabe"));
            start.add(new H4("Startdatum"), new Span(stellenAnzeige.getStartdatum()!=null? stellenAnzeige.getStartdatum().replace("'", ""): "Keine Angabe"));
            range.add(new H4("Dauer"), new Span(stellenAnzeige.getProjektdauer()!=null? stellenAnzeige.getProjektdauer().replace("'", ""): "Keine Angabe"));
            des.add(new H4("Beschreibung"), new Span(stellenAnzeige.getBeschreibung()!=null? stellenAnzeige.getBeschreibung().replace("'", ""): "Keine Angabe"));

            contactButton = new Button("Stellenanzeige kontaktieren");
            contactButton.addClassName("default-btn");

            contactButton.addClickListener(event -> {
                urlAnchor.getElement().callJsFunction("click");
            });

            VerticalLayout keys = new VerticalLayout();
            //keys.addClassName("vaadin-field-container");

            H4 label = new H4("Keywords");
            //label.addClassName("form-label");

            keywords = stellenAnzeige.getKeywords();

            cards = new HorizontalLayout();
            cards.setClassName("cards");

            for(int i = 0; i < keywords.size(); i++) {
                KeywordDTO temp = keywords.get(i);
                temp.setKeywordname(temp.getKeywordname().substring(0, 1).toUpperCase()+temp.getKeywordname().substring(1));
                JobAdvertisementView.KeyCard card = new JobAdvertisementView.KeyCard(temp);
                cards.add(card);
            }

            keys.add(label, cards);

            FormLayout formLayout = new FormLayout();
            formLayout.addClassName("job-box");

            formLayout.add(
                    url, company, technology, qualifications, start, range, des,
                    keys
            );
            formLayout.setColspan(des, 2);// Beschreibung über zwei Spalten erstrecken
            // formLayout.setColspan(keys, 2); // Keywords über zwei Spalten erstrecken

            // Stretch country textfield to full row width
//            formLayout.setColspan(country, 2);
            formLayout.setSizeUndefined();
            this.add(formLayout);
        }
    }

    /**
     * Methode, die nach, der Datenübergabe, die Content des Views füllt
     */
    public void loadContent(){
        boolean reserved = stellenAnzeige.getReserved();

        Span available = new Span();

        String theme = String.format("badge %s", reserved ? "error" : "success");
        available.getElement().setAttribute("theme", theme);
        available.getStyle().set("font-size", "x-large");
        available.setText(reserved ? "Reserviert" : "Verfügbar");

        H1 h1 = new H1(stellenAnzeige.getTitel());

        HorizontalLayout headline = new HorizontalLayout();
        headline.add(h1, available);

        Dialog dialog = new Dialog();

        dialog.setHeaderTitle(String.format("Stellenanzeige \"%s\" löschen?", stellenAnzeige.getTitel()));

        dialog.add("Sind sie sicher, dass sie die Stellenanzeige permanent löchen wollen?");

        Button abortModalButton = new Button("Abbrechen", e -> dialog.close());
        abortModalButton.getStyle().set("margin-right", "auto");
        dialog.getFooter().add(abortModalButton);
        Button deleteModalButton = new Button("Fortfahren", e -> {
            jobControl.deleteStellenanzeige(stellenAnzeige.getJobid());
            dialog.close();
            UtilNavigation.navigateToMain();
        });
        dialog.getFooter().add(deleteModalButton);
        deleteModalButton.addClassName("delete-btn");


        JobAdvertisementView.ShowJobForm form = new JobAdvertisementView.ShowJobForm();
        form.getElement().getStyle().set("Margin", "30px");

        Button addButton = new Button("Editieren");
        addButton.addClickShortcut(Key.ENTER);
        addButton.addClassName("default-btn");

        Button reserveButton = new Button(stellenAnzeige.getReserved() ? "Reservierung entfernen" : "Reservieren", e -> {
            stellenAnzeige.setReserved(!stellenAnzeige.getReserved());
            boolean updateResult = jobInjectService.getJobHinzufuegen() ? jobControl.createStellenanzeige(stellenAnzeige).OK() : jobControl.updateStellenanzeige(stellenAnzeige);
            if (updateResult) {
                jobInjectService.setStellenanzeige(stellenAnzeige);
                UI.getCurrent().getPage().reload();
            }
        });
        reserveButton.addClassName("default-btn");

        Button deleteButton = new Button("Anzeige Löschen");
        deleteButton.addClassName("delete-btn");

        addButton.addClickListener(event -> {
            jobInjectService.setStellenanzeige(stellenAnzeige);
            UtilNavigation.navigateToJobAdvertisementEdit();
        });

        deleteButton.addClickListener(event -> {
            dialog.open();
        });



        HorizontalLayout bigButtons = new HorizontalLayout();
        bigButtons.add(contactButton, reserveButton);

        List<StellenanzeigenDTO> userData = jobControl.getStellenanzeigenForCurrentUser(loginControl.getCurrentUser().getUserid());
        if(jobInjectService.getJobHinzufuegen()){
            bigButtons.add(addButton, deleteButton);
        }else{
            jobInjectService.setStellenanzeige(null);
            for(int i = 0; i<userData.size(); i++){
                if(userData.get(i).getJobid()==stellenAnzeige.getJobid()){
                    bigButtons.add(addButton, deleteButton);
                    jobInjectService.setStellenanzeige(stellenAnzeige);
                }
            }
        }
        jobInjectService.setJobHinzufuegen(false);

        section.add(headline, form, bigButtons);
    }

    public JobAdvertisementView(){
        section = new VerticalLayout();
        section.setWidth("90%");
        section.setAlignItems(Alignment.CENTER);

        HorizontalLayout siteLayout = new HorizontalLayout();
        siteLayout.setAlignSelf(FlexComponent.Alignment.CENTER);
        siteLayout.setSizeFull();
        siteLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        siteLayout.add(section);

        add(siteLayout);
    }

    /**
     * Diese Methode verhindert dass ein Nutzer, der nicht eingeloggt ist, diese View sehen kann
     * Außerdem wird hier das StellenanzeigenDTO aus dem InjectionService ausgelesen
     *
     * @param attachEvent Das Event-Objekt.
     */
    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        if (!(UI.getCurrent().getSession().getAttribute(Globals.CURRENT_USER) instanceof User)) {
            UtilNavigation.navigateToLogin();
        }else{
            try {
                stellenAnzeige = jobInjectService.getStellenanzeige();
                defaultView = (DefaultView) getParent().get();
                defaultView.navigateToJob(stellenAnzeige.getTitel());
                jobInjectService.setStellenanzeige(null);
                //Hinzufügen der Daten in der Tabelle
                loadContent();
            }catch (Exception exception){
                UtilNavigation.navigateToMain();
            };
        }
    }

    public class KeyCard extends HorizontalLayout {
        public KeyCard(KeywordDTO title) {
            Label titleLabel = new Label(title.getKeywordname());
            titleLabel.addClassName("key-label");


            add(titleLabel);

            setPadding(false);
            setMargin(false);
            setSpacing(false);
            setClassName("default-card");
        }
    }
}
