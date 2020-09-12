package ui.views.LoggedUserViews;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import control.AutoErstellenControl;
import control.SessionControl;
import entity.Auto;
import entity.User;
import entity.Vertriebler;
import exceptions.SessionException;
import models.factory.ComponentFactory;
import models.factory.ViewFactory;
import ui.panel.BottomPanel;
import utils.Roles;
import utils.Status;
import utils.Views;

import static models.factory.GridFactory.getConfiguredGrid;

public class MainView extends VerticalLayout implements View {

    private Auto auto = null;

    public void enter(ViewChangeListener.ViewChangeEvent event){
        try {
            SessionControl.controlForLoggedUsers();
            this.addComponent(ViewFactory.createTopPanelForVerticalLayout() );
            this.setUp();
            this.addComponent( new BottomPanel() );
        } catch (SessionException e) {
            Notification.show("Error", e.getMessage(), Notification.Type.WARNING_MESSAGE);
        }

    }

    private void setUp(){
        Label Willkommen = new Label("<h1 style=\"text-align: center; color: #000000;\">Suchen Sie heute noch ein Auto!</h1>\n" +
                "<p>Geben Sie in der Suche die Marke, das Baujahr, oder das Modell ein um ein Auto zu finden</p>\n" +
                "<p>Klicken Sie auf mehr Informationen, wenn Sie genauere Details über das Auto einsehen möchten! &nbsp;</p>\n" +
                "<p>Klicken Sie auf Reservieren/Buchen, um ein Auto zu reservieren und anschließend zu buchen! &nbsp;</p>\n" +
                "<!-- Dieser kommentar ist nur im quelltext-editor sichtbar. -->\n" +
                "<p></p>", ContentMode.HTML);

        this.addComponent(Willkommen);
        this.setComponentAlignment(Willkommen, Alignment.MIDDLE_CENTER);

        // Suchfunktion Textfeld
        TextField SucheAutosFeld = new TextField();
        SucheAutosFeld.setId("suchFeld");
        SucheAutosFeld.setPlaceholder("Nach Autos suchen");
        SucheAutosFeld.setHeight("60");
        SucheAutosFeld.setWidth("850");

        Grid<Auto> ergSuche = getConfiguredGrid();
        ergSuche.setVisible(true);
        ergSuche.setEnabled(true);
        ergSuche.setSizeFull();

        Button AlleAutosButton = ComponentFactory.createButtonWithCaption("Alle Autos anzeigen");
        Button bookCarButton = ComponentFactory.createButtonWithCaption("Auto mieten");


        if(UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER) instanceof Vertriebler){
            bookCarButton.setVisible(false);
        }


        HorizontalLayout horizontalLayout = new HorizontalLayout();

        horizontalLayout.addComponent(AlleAutosButton);
        horizontalLayout.addComponent(bookCarButton);
        horizontalLayout.setComponentAlignment(AlleAutosButton, Alignment.MIDDLE_RIGHT);
        horizontalLayout.setComponentAlignment(bookCarButton, Alignment.MIDDLE_LEFT);

        ergSuche.setStyleGenerator(t -> "v-grid-cell-red");

        SucheAutosFeld.addValueChangeListener(e->{
            if(SucheAutosFeld.isEmpty()){
                Notification.show("Error", "Bitte keine leeren oder ungülten Eingaben!", Notification.Type.WARNING_MESSAGE);
                ergSuche.setItems();
                return;
            }
            ergSuche.setItems(AutoErstellenControl.getAutoWithCriteria(SucheAutosFeld.getValue()));
        });

        AlleAutosButton.addClickListener(e->{
            Integer anzahlAutos = AutoErstellenControl.getAllAutos().size();
            if(anzahlAutos != null){
                Notification.show("Achtung", "Es wurden " + AutoErstellenControl.getAllAutos().size() +  " Autos gefunden", Notification.Type.WARNING_MESSAGE);
                ergSuche.setItems(AutoErstellenControl.getAllAutos());
            }else{
                Notification.show("Error", "Es wurden keine Autos gefunden!", Notification.Type.WARNING_MESSAGE);
            }

        });

        ergSuche.addItemClickListener(autoItemClick->{
            auto = autoItemClick.getItem();

            if(auto.getStatus().equals(Status.BOOKED)){
                bookCarButton.setEnabled(false);
            }else{
                bookCarButton.setEnabled(true);
            }
        });

        bookCarButton.addClickListener( e-> {


            if(auto != null){
                String key = Roles.SELECTED_CAR;
                UI.getCurrent().getSession().setAttribute(key, auto);
                UI.getCurrent().getNavigator().navigateTo(Views.BookingCarView);
            }else{
                Notification.show("Error", "Das Auto kann nicht gefunden werden!", Notification.Type.WARNING_MESSAGE);
            }
        });

        HorizontalLayout SucheLayout = new HorizontalLayout();
        SucheLayout.addComponent(SucheAutosFeld);

        this.addComponent( new VerticalLayout() );
        this.addComponent(SucheLayout);
        this.setComponentAlignment(SucheLayout,Alignment.BOTTOM_CENTER);

        this.addComponent(ergSuche);
        this.addComponent(horizontalLayout);
        this.setComponentAlignment(horizontalLayout,Alignment.MIDDLE_CENTER);

        setMargin(true);
    }

}
