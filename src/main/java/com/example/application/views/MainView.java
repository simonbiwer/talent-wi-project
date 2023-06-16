package com.example.application.views;

import com.example.application.controls.JobControl;
import com.example.application.controls.LoginControl;
import com.example.application.dtos.KeywordDTO;
import com.example.application.dtos.StellenanzeigenDTO;
import com.example.application.dtos.impl.KeywordDTOImpl;
import com.example.application.utils.Utils;
import com.example.application.entities.User;
import com.example.application.layout.DefaultView;
import com.example.application.utils.InjectService;
import com.example.application.utils.UtilNavigation;
import com.example.application.utils.Globals;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.*;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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
    private InjectService jobInjectService;

    @Autowired
    private LoginControl loginControl;

    Grid<StellenanzeigenDTO> grid;

    Select<String> keywordSelect = new Select<>();

    private List<KeywordDTO> keywords;

    private List<KeywordDTO> keywordsAll;
    private HorizontalLayout cards;
    private  FilterFormLayout filter;
    private Accordion accordion;
    private Checkbox ownToggle;
    private TextField value;
    private Select<String> select;

    public MainView() {

        setWidthFull();
        setHeightFull();

        setMargin(true);
        setHorizontalComponentAlignment(Alignment.CENTER);
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        accordion = new Accordion();

        filter = new FilterFormLayout();
        accordion.open(0);
        filter.addClassName("filter-layout");

        accordion.add("Filter", filter);

        grid = new Grid<>(StellenanzeigenDTO.class, false);


        grid.addColumn(StellenanzeigenDTO::getTitel).setHeader("Titel");
        grid.addColumn(StellenanzeigenDTO::getUnternehmen).setHeader("Unternehmen");
        grid.addColumn(StellenanzeigenDTO::getStartdatum).setHeader("Startdatum");
        grid.addComponentColumn(stellenAnzeige -> createStatusIcon(!stellenAnzeige.getReserved()))
                .setHeader("Status");



        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        grid.setSelectionMode(Grid.SelectionMode.NONE);

        grid.addItemClickListener(event -> {
            jobInjectService.setStellenanzeige(event.getItem());
            UtilNavigation.navigateToJobAdvertisement();
        });

        addClassName("remove-Vaadin");
        this.getStyle().set("gap", "0");
        add(accordion, grid);
    }

    /**
     * Diese Klasse dient als Layout für den Filter und erstellt die Elemente
     */
    class FilterFormLayout extends HorizontalLayout{
        FilterFormLayout(){
            keywords = new ArrayList<>();

            value = new TextField("Wert:");

            select = new Select<>();
            select.setEmptySelectionAllowed(true);
            select.setLabel("Filtern nach:");
            select.setItems(Utils.capitalizeString(Globals.Attributes.TITEL), Utils.capitalizeString(Globals.Attributes.UNTERNEHMEN), Utils.capitalizeString(Globals.Attributes.QUALIFIKATIONEN), Utils.capitalizeString(Globals.Attributes.TECHNOLOGIE), Utils.capitalizeString(Globals.Attributes.STARTDATUM), Utils.capitalizeString(Globals.Attributes.PROJEKTDAUER), Utils.capitalizeString(Globals.Attributes.BESCHREIBUNG));
            select.setPlaceholder("Wert auswählen");

            keywordSelect.setEmptySelectionAllowed(true);
            keywordSelect.setPlaceholder("Keyword auswählen");
            keywordSelect.setLabel("Keywords:");

            keywordSelect.addValueChangeListener(e -> {
                addKeyword(e.getValue());
                keywordSelect.setValue(null);
            });


            VerticalLayout keyContainer = new VerticalLayout();
            keyContainer.addClassName("key-vertical");

            cards = new HorizontalLayout();
            cards.setClassName("cards");

            keyContainer.add(cards);

            Button applyFilterbtn = new Button("Filter anwenden", e->{
                jobInjectService.setFilter(select.getValue().toLowerCase(Locale.ROOT), value.getValue(), keywords, ownToggle.getValue());

                Boolean valueSelected = (select.getValue()!=null)&&(value.getValue()!=null);
                Boolean keywordSelected = keywords.size()>0;

                List<StellenanzeigenDTO> newStellenanzeigen;

                if(valueSelected && keywordSelected){
                    newStellenanzeigen = jobControl.filterJobs(select.getValue().toLowerCase(Locale.ROOT), value.getValue(), keywords);
                }else if(valueSelected){
                    newStellenanzeigen = jobControl.filterJobs(select.getValue().toLowerCase(Locale.ROOT), value.getValue());
                }else if(keywordSelected){
                    newStellenanzeigen = jobControl.filterJobs(keywords);
                }else{
                    newStellenanzeigen = jobControl.readAllStellenanzeigen();
                }

                checkIfOwn(newStellenanzeigen);

            });
            applyFilterbtn.addClassName("default-btn");
            applyFilterbtn.addThemeName("apply-filter-btn");

            Button resetFilterBtn = new Button("Filter zurücksetzen", e -> {
                keywords.clear();
                cards.removeAll();
                jobInjectService.setFilter(null, null, keywords, false);
                grid.setItems(jobControl.readAllStellenanzeigen());
                // Felder leeren
                ownToggle.setValue(false);
                select.clear();
                value.clear();
                keywordSelect.clear();
            });

            resetFilterBtn.addClassName("delete-btn");

            ownToggle = new Checkbox("Zeige nur eigene Stellenanzeigen");

            HorizontalLayout layoutHorizontal = new HorizontalLayout();
            layoutHorizontal.addClassName("remove-Vaadin");
            layoutHorizontal.add(select, value, keywordSelect, keyContainer);

            VerticalLayout layoutVertical = new VerticalLayout();
            layoutVertical.addClassName("remove-Vaadin");
            layoutVertical.add(layoutHorizontal, ownToggle);


            add(layoutVertical, applyFilterbtn, resetFilterBtn);
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
            //Hinzufügen der Daten in der Tabelle
            jobInjectService.setJobHinzufuegen(false);
            keywordsAll = jobControl.getAllKeywords();
            List<String> toStrings = new ArrayList<>();
            for (KeywordDTO element : keywordsAll){
                toStrings.add(Utils.capitalizeString(element.getKeywordname()));
            }
            keywordSelect.setItems(toStrings);
            loadFilterSettings();
        }
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

    /**
     * Methode, die ein Keyword zur Liste hinzufügt -> gleich zu AddJobView
     */
    private void addKeyword(String keyText) {
        KeywordDTOImpl keyword = new KeywordDTOImpl();
        keyword.setKeywordname(keyText);
        MainView.KeyCard card = new MainView.KeyCard(keyword);
        if (!(keyText==null)) {
            cards.add(card);
            keyword.setKeywordname(keyword.getKeywordname().toLowerCase(Locale.ROOT)); //Warum lowercase? Um redundante Keywords zu vermeiden? Ja
            keywords.add(keyword);
        }
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

    /**
     * Methode um die Filtereinstsellungen zu laden
     */
    public void loadFilterSettings(){
        InjectService.Filter filter = jobInjectService.getFilter();

        String filterType = filter.getFilterType();
        String filterValue = filter.getFilterValue();
        Boolean toggle = filter.getOwn();
        keywords = filter.getKeywords();

        for(int i = 0; i < keywords.size(); i++) {
            KeywordDTO temp = keywords.get(i);
            temp.setKeywordname(temp.getKeywordname().substring(0, 1).toUpperCase()+temp.getKeywordname().substring(1));
            MainView.KeyCard card = new MainView.KeyCard(temp);
            cards.add(card);
        }

        select.setValue(filterType);
        value.setValue(filterValue==null ? "" : filterValue);
        ownToggle.setValue(toggle);

        Boolean valueSelected = (filterType!=null)&&(filterValue!=null);
        Boolean keywordSelected = keywords.size()>0;

        List<StellenanzeigenDTO> newStellenanzeigen;

        if(valueSelected && keywordSelected){
            newStellenanzeigen = jobControl.filterJobs(filterType, filterValue, keywords);
        }else if(valueSelected){
            newStellenanzeigen = jobControl.filterJobs(filterType, filterValue);
        }else if(keywordSelected){
            newStellenanzeigen = jobControl.filterJobs(keywords);
        }else{
            newStellenanzeigen = jobControl.readAllStellenanzeigen();
            accordion.close();
        }

        checkIfOwn(newStellenanzeigen);
    }

    /**
     * Methode um den Join aus eigenen Stellenanzeigen und den gefilterten zu erzeugen
     * @param newStellenanzeigen    -   Liste der gefilterten Stellenanzeigen
     */
    private void checkIfOwn(List<StellenanzeigenDTO> newStellenanzeigen){
        if(ownToggle.getValue()){
            List<StellenanzeigenDTO> ownStellenanzeigen = jobControl.getStellenanzeigenForCurrentUser(loginControl.getCurrentUser().getUserid());
            List<StellenanzeigenDTO> mergedList = new ArrayList<>();
            for (StellenanzeigenDTO jobInAttributeList : newStellenanzeigen){
                for (StellenanzeigenDTO jobInKeywordList : ownStellenanzeigen){
                    if (jobInAttributeList.getJobid() == jobInKeywordList.getJobid()){
                        mergedList.add(jobInAttributeList);
                    }
                }
            }
            grid.setItems(mergedList);
        }else {
            grid.setItems(newStellenanzeigen);
        }
    }
}
