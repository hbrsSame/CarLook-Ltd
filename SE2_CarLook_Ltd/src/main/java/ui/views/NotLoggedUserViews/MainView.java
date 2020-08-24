package ui.views.NotLoggedUserViews;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import control.SessionControl;
import entity.Auto;
import exceptions.SessionException;
import models.factory.ComponentFactory;
import ui.panel.BottomPanel;
import ui.panel.TopPanel;
import utils.Views;

import static models.factory.GridFactory.getConfiguredGrid;

public class MainView extends VerticalLayout implements View {

    public void enter(ViewChangeListener.ViewChangeEvent event){
        try {
            SessionControl.controlForNotLoggedSession();
            this.addComponent( new TopPanel());
            this.setUp();
            this.addComponent( new BottomPanel() );
        } catch (SessionException e) {
            Notification.show("Error", e.getMessages(), Notification.Type.WARNING_MESSAGE);
        }


    }

    private void setUp(){
        Label Willkommen = new Label("<h1 style=\"text-align: center; color: #000000;\">Willkommen auf CarLook!</h1>\n" +
                "<p>Ihre Nr 1. Suchplattform für Autos auf dem Markt. Registrieren Sie sich heute noch</p>\n" +
                "<p>und profitieren Sie von über 100 Vorteilen als Endkunde und Vertriebler &nbsp;</p>\n" +
                "<!-- Dieser kommentar ist nur im quelltext-editor sichtbar. -->\n" +
                "<p></p>", ContentMode.HTML);

        this.addComponent(Willkommen);
        this.setComponentAlignment(Willkommen, Alignment.MIDDLE_CENTER);

        // Suchfunktion Textfeld
        TextField SucheAutosFeld = new TextField();
        SucheAutosFeld.setPlaceholder("Suche Eine Stellenausschreibung");
        SucheAutosFeld.setHeight("60");
        SucheAutosFeld.setWidth("850");

        Grid<Auto> ergSuche = getConfiguredGrid();
        ergSuche.setVisible(false);
        ergSuche.setEnabled(true);
        ergSuche.setSizeFull();

        Button mehrButton = ComponentFactory.createButtonWithCaption("Mehr Informationen");
        Button bewerben = ComponentFactory.createButtonWithCaption("Auto mieten");

        HorizontalLayout horizontalLayout = new HorizontalLayout();

        horizontalLayout.addComponent(mehrButton);
        horizontalLayout.addComponent(bewerben);
        horizontalLayout.setComponentAlignment(mehrButton, Alignment.MIDDLE_RIGHT);
        horizontalLayout.setComponentAlignment(bewerben, Alignment.MIDDLE_LEFT);



        HorizontalLayout SucheLayout = new HorizontalLayout();
        SucheLayout.addComponent(SucheAutosFeld);
        SucheLayout.setComponentAlignment(SucheAutosFeld, Alignment.MIDDLE_LEFT);
        SucheLayout.addComponent(SucheAutosFeld);
        SucheLayout.setComponentAlignment(SucheAutosFeld, Alignment.BOTTOM_RIGHT);

        this.addComponent(SucheLayout);
        this.setComponentAlignment(SucheLayout,Alignment.MIDDLE_CENTER);

        this.addComponent(ergSuche);
        this.addComponent(horizontalLayout);
        this.setComponentAlignment(horizontalLayout,Alignment.MIDDLE_CENTER);

        Button welcome = new Button("Willkommen");
        welcome.setCaption("Willkommen");

        Button LoginView = new Button("LoginView");

        LoginView.addClickListener(e->{
            UI.getCurrent().getNavigator().navigateTo(Views.LoginView);
        });

        TextField FirstTextField = new TextField();
        FirstTextField.setCaption("FirstTextField");

        VerticalLayout componentLayout = new VerticalLayout();
        componentLayout.addComponents(FirstTextField, welcome, LoginView);
        componentLayout.setComponentAlignment(FirstTextField, Alignment.TOP_CENTER);
        componentLayout.setComponentAlignment(welcome, Alignment.TOP_CENTER);
        componentLayout.setComponentAlignment(LoginView, Alignment.MIDDLE_CENTER);

        this.addComponent(componentLayout);
        this.setSizeFull();
        setMargin(true);
    }

}
