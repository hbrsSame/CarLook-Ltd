package ui.views.NotLoggedUserViews;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import control.SessionControl;
import entity.Auto;
import exceptions.SessionException;
import models.factory.ComponentFactory;
import models.factory.ViewFactory;
import ui.panel.BottomPanel;
import ui.panel.TopPanel;
import utils.Views;

import static models.factory.GridFactory.getConfiguredGrid;

public class MainView extends VerticalLayout implements View {

    public void enter(ViewChangeListener.ViewChangeEvent event){
        try {
            SessionControl.controlForLoggedUsers();
            this.addComponent(ViewFactory.createTopPanelForVerticalLayout() );
            this.setUp();
            this.addComponent( new BottomPanel() );
        } catch (SessionException e) {
            Notification.show("Error", e.getMessages(), Notification.Type.WARNING_MESSAGE);
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
        SucheAutosFeld.setPlaceholder("Nach Autos suchen");
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

        this.addComponent( new VerticalLayout() );
        this.addComponent(SucheLayout);
        this.setComponentAlignment(SucheLayout,Alignment.BOTTOM_CENTER);

        this.addComponent(ergSuche);
        this.addComponent(horizontalLayout);
        this.setComponentAlignment(horizontalLayout,Alignment.MIDDLE_CENTER);

        setMargin(true);
    }

}
