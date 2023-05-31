package com.example.application.views;

import com.example.application.controls.JobControl;
import com.example.application.dtos.InsertJobResult;
import com.example.application.dtos.KeywordDTO;
import com.example.application.dtos.StellenanzeigenDTO;
import com.example.application.dtos.impl.KeywordDTOImpl;
import com.example.application.dtos.impl.StellenanzeigenDTOImpl;
import com.example.application.entities.User;
import com.example.application.layout.DefaultView;
import com.example.application.utils.Globals;
import com.example.application.utils.JobInjectService;
import com.example.application.utils.SettingsService;
import com.example.application.utils.UtilNavigation;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * View zur Editierung von Stellenanzeigen -> überschreibt diese in der Datenbank
 * @author hh 31.05.23
 * @since 31.05.23
 */
@CssImport(value = "./styles/layout-style.css")
@Route(value = Globals.Pages.JOBADVERTISEMENT_EDIT_VIEW, layout = DefaultView.class)
@PageTitle(Globals.PageTitles.JOBADVERTISEMENT_EDIT_TITLE)
public class JobAdvertisementEditView extends VerticalLayout {

    @Autowired
    private JobControl jobControl;

    @Autowired
    private JobInjectService jobInjectService;

    @Autowired
    private SettingsService settingsService;

    private StellenanzeigenDTO stellenAnzeige;

    TextField title = new TextField("Titel");
    TextField url = new TextField("URL");
    TextField company = new TextField("Unternehmen");
    TextField technology = new TextField("Technologien");
    TextField qualifications = new TextField("Qualifikationen");
    TextField start = new TextField("Startdatum");
    TextField range = new TextField("Dauer");
    TextArea des = new TextArea("Beschreibung");
    private TextField keywordInputField;
    private List<KeywordDTO> keywords;
    private Button addButton;
    private Button saveBtn;
    private Button backBtn;
    private HorizontalLayout cards;

    private VerticalLayout section;
    private Binder<StellenanzeigenDTOImpl> binderJob = new Binder(StellenanzeigenDTOImpl.class);

    /**
     * Form-Klasse, welches schon mit Daten gefüllt wird
     */
    class EditJobForm extends Div {
        EditJobForm() {

            title.setValue(stellenAnzeige.getTitel());
            url.setValue(stellenAnzeige.getUrl());
            company.setValue(stellenAnzeige.getUnternehmen() != null ? stellenAnzeige.getUnternehmen().replace("'", "") : "Keine Angabe");
            technology.setValue(stellenAnzeige.getTechnologien() != null ? stellenAnzeige.getTechnologien().replace("'", "") : "Keine Angabe");
            qualifications.setValue(stellenAnzeige.getQualifikation() != null ? stellenAnzeige.getQualifikation().replace("'", "") : "Keine Angabe");
            start.setValue(stellenAnzeige.getStartdatum() != null ? stellenAnzeige.getStartdatum().replace("'", "") : "Keine Angabe");
            range.setValue(stellenAnzeige.getProjektdauer() != null ? stellenAnzeige.getProjektdauer().replace("'", "") : "Keine Angabe");
            des.setValue(stellenAnzeige.getBeschreibung() != null ? stellenAnzeige.getBeschreibung().replace("'", "") : "Keine Angabe");

            title.setRequiredIndicatorVisible(true);
            url.setRequiredIndicatorVisible(true);
            url.setErrorMessage("URL ungültig"); // Setzt die benutzerdefinierte Fehlermeldung für ungültige Eingaben

            Div keys = new Div();
            keys.addClassName("vaadin-field-container");

            Label label = new Label("Keywords");
            label.addClassName("form-label");

            VerticalLayout keyContainer = new VerticalLayout();
            keyContainer.addClassName("key-vertical");

            keywords = stellenAnzeige.getKeywords();
            keywordInputField = new TextField();
            keywordInputField.setWidth("100%");
            addButton = new Button("Hinzufügen");

            cards = new HorizontalLayout();
            cards.setClassName("cards");

            HorizontalLayout cardButtons = new HorizontalLayout();
            cardButtons.addClassName("key-vertical");
            cardButtons.add(keywordInputField, addButton);

            for (int i = 0; i < keywords.size(); i++) {
                KeywordDTO temp = keywords.get(i);
                temp.setKeywordname(temp.getKeywordname().substring(0, 1).toUpperCase() + temp.getKeywordname().substring(1));
                JobAdvertisementEditView.KeyCard card = new JobAdvertisementEditView.KeyCard(temp);
                cards.add(card);
            }

            addButton.addClickListener(e -> {
                addKeyword();
            });

            keyContainer.add(cardButtons, cards);

            keys.add(label, keyContainer);

            saveBtn.addClickListener(e -> {
                if (title.isInvalid()) {
                    Notification.show("Bitte geben Sie einen gültogen Titel an!");
                } else if (title.isEmpty() || url.isEmpty()) {
                    Notification.show("Bitte alle Pflichtfelder ausfüllen!");
                } else {
                    StellenanzeigenDTO job = binderJob.getBean();
                    stellenAnzeige.setTitel(job.getTitel());
                    stellenAnzeige.setUrl(job.getUrl());
                    stellenAnzeige.setUnternehmen(job.getUnternehmen());
                    stellenAnzeige.setTechnologien(job.getTechnologien());
                    stellenAnzeige.setQualifikation(job.getQualifikation());
                    stellenAnzeige.setStartdatum(job.getStartdatum());
                    stellenAnzeige.setProjektdauer(job.getProjektdauer());
                    stellenAnzeige.setBeschreibung(job.getBeschreibung());
                    stellenAnzeige.setKeywords(keywords);
                    try {
                        if(settingsService.getJobHinzufuegen()){
                            InsertJobResult result = jobControl.createStellenanzeige(stellenAnzeige);
                            if (result.OK()) {
                                jobInjectService.setStellenanzeige(stellenAnzeige);
                                UtilNavigation.navigateToJobAdvertisement();
                            }else{
                                Notification.show(result.getMessage());
                            }
                        } else{
                            boolean updateResult = jobControl.updateStellenanzeige(stellenAnzeige);
                            if (updateResult) {
                                jobInjectService.setStellenanzeige(stellenAnzeige);
                                UtilNavigation.navigateToJobAdvertisement();
                            }
                        }
                    } catch (Exception exception) {
                        Notification.show("Erstellen fehlgeschlagen");
                        exception.printStackTrace();
                    }
                }
            });

            backBtn.addClickListener(event -> {
               UtilNavigation.navigateToJobAdvertisement();
            });

            FormLayout formLayout = new FormLayout();
            formLayout.add(
                    title, url, company, technology, qualifications, start, range, des,
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

    public JobAdvertisementEditView() {

        section = new VerticalLayout();
        section.setWidth("75%");
        section.setAlignItems(Alignment.CENTER);

        HorizontalLayout siteLayout = new HorizontalLayout();
        siteLayout.setAlignSelf(FlexComponent.Alignment.CENTER);
        siteLayout.setSizeFull();
        siteLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        siteLayout.add(section);

        add(siteLayout);


        binderJob.setBean(new StellenanzeigenDTOImpl());
        binderJob.forField(title)
                .withValidator(bindervorname -> bindervorname.length() > 0, "Bitte Titel angeben!")
                .bind(StellenanzeigenDTOImpl::getTitel, StellenanzeigenDTOImpl::setTitel);
        binderJob.forField(url)
                .withValidator(bindernachname -> bindernachname.length() > 0, "Bitte URL angeben!")
                .bind(StellenanzeigenDTOImpl::getUrl, StellenanzeigenDTOImpl::setUrl);
        binderJob.forField(company)
                .bind(StellenanzeigenDTOImpl::getUnternehmen, StellenanzeigenDTOImpl::setUnternehmen);
        binderJob.forField(technology)
                .bind(StellenanzeigenDTOImpl::getTechnologien, StellenanzeigenDTOImpl::setTechnologien);
        binderJob.forField(qualifications)
                .bind(StellenanzeigenDTOImpl::getQualifikation, StellenanzeigenDTOImpl::setQualifikation);
        binderJob.forField(start)
                .bind(StellenanzeigenDTOImpl::getStartdatum, StellenanzeigenDTOImpl::setStartdatum);
        binderJob.forField(range)
                .bind(StellenanzeigenDTOImpl::getProjektdauer, StellenanzeigenDTOImpl::setProjektdauer);
        binderJob.forField(des)
                .bind(StellenanzeigenDTOImpl::getBeschreibung, StellenanzeigenDTOImpl::setBeschreibung);

        add(siteLayout);
    }

    /**
     * Methode, die nach, der Datenübergabe, die Content des Views füllt
     */
    public void loadContent() {
        H1 h1 = new H1(stellenAnzeige.getTitel());

        saveBtn = new Button(settingsService.getJobHinzufuegen() ? "Stellenanzeige speichern" : "Änderungen speichern");
        saveBtn.addClickShortcut(Key.ENTER);
        saveBtn.addClassName("default-btn");

        backBtn = new Button("Zurück");
        backBtn.addClassName("default-btn");

        JobAdvertisementEditView.EditJobForm form = new JobAdvertisementEditView.EditJobForm();
        form.getElement().getStyle().set("Margin", "30px");

        HorizontalLayout bigButtons = new HorizontalLayout();
        if(settingsService.getJobHinzufuegen()){
            bigButtons.add(saveBtn);
        }else{
            bigButtons.add(backBtn, saveBtn);
        }
        section.add(h1, form, bigButtons);
    }

    /**
     * Methode, die ein Keyword zur Liste hinzufügt -> gleich zu AddJobView
     */
    private void addKeyword() {
        KeywordDTOImpl keyword = new KeywordDTOImpl();
        keyword.setKeywordname(keywordInputField.getValue());
        KeyCard card = new KeyCard(keyword);
        if (!keyword.getKeywordname().isEmpty()) {
            cards.add(card);
            keyword.setKeywordname(keyword.getKeywordname().toLowerCase()); //Warum lowercase? Um redundante Keywords zu vermeiden?
            keywords.add(keyword);
            keywordInputField.setValue("");
        }
    }

    /**
     * Methode, die nach öffnen des Views, den Content mittels Dependancy-Injection holt
     * @param attachEvent Event-Object des Attack Vorgangs
     */
    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        if (!(UI.getCurrent().getSession().getAttribute(Globals.CURRENT_USER) instanceof User)) {
            UtilNavigation.navigateToLogin();
        }
        //Hinzufügen der Daten in der Tabelle
        stellenAnzeige = jobInjectService.getStellenanzeige();
        loadContent();
    }

    /**
     * Keyword-Klasse für die Liste -> gleich zu AddJobView
     */
    public class KeyCard extends HorizontalLayout {
        public KeyCard(KeywordDTO title) {
            Label titleLabel = new Label(title.getKeywordname());
            Button remove = new Button(VaadinIcon.CLOSE.create());
            titleLabel.addClassName("key-label");
            remove.addClassName("key-btn");

            remove.addClickListener(e -> {
                keywords.remove(title);
                cards.remove(this);
            });

            add(titleLabel, remove);

            setPadding(false);
            setMargin(false);
            setSpacing(false);
            setClassName("default-card");
        }
    }
}

