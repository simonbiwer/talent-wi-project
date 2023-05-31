package com.example.application.views;


import com.example.application.controls.JobControl;
import com.example.application.dtos.KeywordDTO;
import com.example.application.dtos.StellenanzeigenDTO;
import com.example.application.entities.User;
import com.example.application.layout.DefaultView;
import com.example.application.utils.Globals;
import com.example.application.utils.JobInjectService;
import com.example.application.utils.UtilNavigation;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
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
    private JobInjectService jobInjectService;

    private StellenanzeigenDTO stellenAnzeige;
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
    private HorizontalLayout cards;

    private VerticalLayout section;

    class ShowJobForm extends Div {
        ShowJobForm() {

            //Werte werden eingefügt in die View
            url.setValue(stellenAnzeige.getUrl()!=null? stellenAnzeige.getUrl().replace("'", ""): "Keine Angabe");
            url.setReadOnly(true);
            company.setValue(stellenAnzeige.getUnternehmen()!=null? stellenAnzeige.getUnternehmen().replace("'", ""): "Keine Angabe");
            company.setReadOnly(true);
            technology.setValue(stellenAnzeige.getTechnologien()!=null? stellenAnzeige.getTechnologien().replace("'", ""): "Keine Angabe");
            technology.setReadOnly(true);
            qualifications.setValue(stellenAnzeige.getQualifikation()!=null? stellenAnzeige.getQualifikation().replace("'", ""): "Keine Angabe");
            qualifications.setReadOnly(true);
            start.setValue(stellenAnzeige.getStartdatum()!=null? stellenAnzeige.getStartdatum().replace("'", ""): "Keine Angabe");
            start.setReadOnly(true);
            range.setValue(stellenAnzeige.getProjektdauer()!=null? stellenAnzeige.getProjektdauer().replace("'", ""): "Keine Angabe");
            range.setReadOnly(true);
            des.setValue(stellenAnzeige.getBeschreibung()!=null? stellenAnzeige.getBeschreibung().replace("'", ""): "Keine Angabe");
            des.setReadOnly(true);


            Div keys = new Div();
            keys.addClassName("vaadin-field-container");

            Label label = new Label("Keywords");
            label.addClassName("form-label");

            VerticalLayout keyContainer = new VerticalLayout();
            keyContainer.addClassName("key-vertical");

            keywords = stellenAnzeige.getKeywords();

            cards = new HorizontalLayout();
            cards.setClassName("cards");

            HorizontalLayout cardButtons = new HorizontalLayout();
            cardButtons.addClassName("key-vertical");

            for(int i = 0; i < keywords.size(); i++) {
                KeywordDTO temp = keywords.get(i);
                temp.setKeywordname(temp.getKeywordname().substring(0, 1).toUpperCase()+temp.getKeywordname().substring(1));
                JobAdvertisementView.KeyCard card = new JobAdvertisementView.KeyCard(temp);
                cards.add(card);
            }

            keyContainer.add(cardButtons, cards);

            keys.add(label, keyContainer);

            FormLayout formLayout = new FormLayout();
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

    public void loadContent(){
        H1 h1 = new H1(stellenAnzeige.getTitel());

        JobAdvertisementView.ShowJobForm form = new JobAdvertisementView.ShowJobForm();
        form.getElement().getStyle().set("Margin", "30px");
        Button addButton = new Button("Editieren");
        addButton.addClickShortcut(Key.ENTER);
        addButton.addClassName("default-btn");

        addButton.addClickListener(event -> {
            UtilNavigation.navigateToJobAdvertisementEdit();
        });

        section.add(h1, form, addButton);
    }

    public JobAdvertisementView(){
        section = new VerticalLayout();
        section.setWidth("75%");
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
        }
        //Hinzufügen der Daten in der Tabelle
        stellenAnzeige = jobInjectService.getStellenanzeige();
        loadContent();
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
