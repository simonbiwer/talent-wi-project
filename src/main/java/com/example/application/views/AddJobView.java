package com.example.application.views;

import com.example.application.controls.ChatGPTControl;
import com.example.application.dtos.KeywordDTO;
import com.example.application.dtos.impl.KeywordDTOImpl;
import com.example.application.dtos.impl.StellenanzeigenDTOImpl;
import com.example.application.entities.User;
import com.example.application.layout.DefaultView;
import com.example.application.utils.Globals;
import com.example.application.utils.UtilNavigation;
import com.example.application.utils.Utils;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
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

import java.util.ArrayList;
import java.util.List;

/**
 * View zum Einfügen einer neuen Stellenanzeige
 */

@CssImport(value = "./styles/layout-style.css")
@Route(value = Globals.Pages.JOBADD_VIEW, layout = DefaultView.class)
@PageTitle(Globals.PageTitles.JOBADD_PAGE_TITLE)
public class AddJobView extends VerticalLayout {

    @Autowired
    private ChatGPTControl chatGPTControl;

    TextField title = new TextField("Titel");
    TextField url = new TextField("URL");
    TextArea des = new TextArea("Beschreibung");
    private TextField keywordInputField;
    private List<KeywordDTO> keywords;
    private Button addButton;
    private HorizontalLayout cards;
    private Binder<StellenanzeigenDTOImpl> binderJob = new Binder(StellenanzeigenDTOImpl.class);

    class AddJobForm extends Div {
        AddJobForm() {
            title.setRequiredIndicatorVisible(true);
            url.setRequiredIndicatorVisible(true);
            url.setErrorMessage("URL ungültig"); // Setzt die benutzerdefinierte Fehlermeldung für ungültige Eingaben

            Div keys = new Div();
            keys.addClassName("vaadin-field-container");

            Label label = new Label("Keywords");
            label.addClassName("form-label");

            VerticalLayout keyContainer = new VerticalLayout();
            keyContainer.addClassName("key-vertical");

            keywords = new ArrayList<>();
            keywordInputField = new TextField();
            keywordInputField.setWidth("100%");
            addButton = new Button("Hinzufügen");

            cards = new HorizontalLayout();
            cards.setClassName("cards");

            HorizontalLayout cardButtons = new HorizontalLayout();
            cardButtons.addClassName("key-vertical");
            cardButtons.add(keywordInputField, addButton);

            addButton.addClickListener(e -> {
                addKeyword();
            });

            keyContainer.add(cardButtons, cards);

            keys.add(label, keyContainer);

            FormLayout formLayout = new FormLayout();
            formLayout.add(
                    title, url, des,
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

    public AddJobView() {

        VerticalLayout section = new VerticalLayout();
        section.setWidth("75%");
        section.setAlignItems(Alignment.CENTER);

        H1 h1 = new H1("Stellenanzeige erstellen");

        AddJobView.AddJobForm form = new AddJobView.AddJobForm();
        form.getElement().getStyle().set("Margin", "30px");
        Button addButton = new Button("Erstellen");
        addButton.addClickShortcut(Key.ENTER);
        addButton.addClassName("default-btn");

        section.add(h1, form, addButton);

        HorizontalLayout siteLayout = new HorizontalLayout();
        siteLayout.setAlignSelf(FlexComponent.Alignment.CENTER);
        siteLayout.setSizeFull();
        siteLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        siteLayout.add(section);

        binderJob.setBean(new StellenanzeigenDTOImpl());
        binderJob.forField(title)
                .withValidator(titel-> titel.length() > 0, "Bitte Titel angeben!")
                .bind(StellenanzeigenDTOImpl::getTitel, StellenanzeigenDTOImpl::setTitel);
        binderJob.forField(url)
                .withValidator(url -> url.length() > 0, "Bitte URL angeben!")
                .withValidator(url -> Utils.isURLValid(url), "URL ungültig!")
                .bind(StellenanzeigenDTOImpl::getUrl, StellenanzeigenDTOImpl::setUrl);
        binderJob.forField(des)
                .bind(StellenanzeigenDTOImpl::getBeschreibung, StellenanzeigenDTOImpl::setBeschreibung);


        addButton.addClickListener(e -> {
            if (title.isInvalid()) {
                Notification.show("Bitte geben Sie einen gültogen Titel an!");
            } else if (title.isEmpty() || url.isEmpty()) {
                Notification.show("Bitte alle Pflichtfelder ausfüllen!");
            } else {
                StellenanzeigenDTOImpl job = binderJob.getBean();
                job.setKeywords(keywords);
              /*  job.setStartdatum(start.getValue().toString());*/
                try {
                    boolean result = chatGPTControl.crawlDataWithChatGPT(job);
                    if (result){
                        Notification.show("Crawling erfolgreich");
                    } else {
                        Notification.show("Fehler beim crawlen");
                    }
                } catch (Exception exception) {
                    Notification.show("Erstellen fehlgeschlagen");
                    exception.printStackTrace();
                }
            }
        });

        add(siteLayout);
    }


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


    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        if (!(UI.getCurrent().getSession().getAttribute(Globals.CURRENT_USER) instanceof User)) {
            UtilNavigation.navigateToLogin();
        }
    }

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
