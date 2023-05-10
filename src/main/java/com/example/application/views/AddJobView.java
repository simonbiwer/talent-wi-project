package com.example.application.views;

import com.example.application.controls.AddJobControl;
import com.example.application.dtos.impl.StellenanzeigenDTOImpl;
import com.example.application.dtos.impl.UserDTOImpl;
import com.example.application.entities.Keyword;
import com.example.application.entities.User;
import com.example.application.layout.DefaultView;
import com.example.application.utils.Globals;
import com.example.application.utils.UtilNavigation;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@CssImport(value = "./styles/layout-style.css")
@Route(value = Globals.Pages.JOBADD_VIEW, layout = DefaultView.class)
@PageTitle(Globals.PageTitles.JOBADD_PAGE_TITLE)
public class AddJobView extends VerticalLayout {

    @Autowired
    private AddJobControl addJobControl;

    EmailField email = new EmailField("E-Mail");
    TextField firstname = new TextField("Vorname");
    TextField lastname = new TextField("Nachname");
    PasswordField password = new PasswordField("Password");
    PasswordField password2 = new PasswordField("Passwort bestätigen");

    private TextField keywordInputField;

    private List<String> keywords;

    private Button addButton;

    private HorizontalLayout cards;

    private Binder<StellenanzeigenDTOImpl> binderUser = new Binder(StellenanzeigenDTOImpl.class);

    public AddJobView() {
        VerticalLayout formular = new VerticalLayout();

        keywords = new ArrayList<>();
        keywordInputField = new TextField();
        addButton = new Button("Hinzufügen");

        cards = new HorizontalLayout();

        addButton.addClickListener(e -> {
            addKeyword();
        });

        formular.add(cards, keywordInputField, addButton);

        this.add(formular);
    }

    private void addKeyword() {
        String keyword = keywordInputField.getValue();
        KeyCard card = new KeyCard(keyword);
        if (!keyword.isEmpty()) {
            cards.add(card);
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
        public KeyCard(String title){
            Label titleLabel = new Label(title);
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
