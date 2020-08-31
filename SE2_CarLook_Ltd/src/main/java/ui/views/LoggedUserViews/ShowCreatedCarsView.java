package ui.views.LoggedUserViews;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import control.AutoErstellenControl;
import control.SessionControl;
import entity.Auto;
import entity.User;
import exceptions.SessionException;
import models.factory.ComponentFactory;
import models.factory.ViewFactory;
import ui.panel.BottomPanel;
import utils.Roles;
import utils.Views;

import java.util.List;

import static models.factory.GridFactory.getConfiguredGrid;

public class ShowCreatedCarsView extends VerticalLayout implements View {

    public void enter(ViewChangeListener.ViewChangeEvent event){
        try {

            SessionControl.controlForLoggedUsers();
            this.addComponent(ViewFactory.createTopPanelForVerticalLayout() );
            this.setUp();
            this.addComponent( new BottomPanel());
        } catch (SessionException e) {
            e.printStackTrace();
        }

    }

    private void setUp(){

        Integer anzahlAutos = AutoErstellenControl.getAllAutos().size();
        String anzahl = "";
        if(anzahlAutos != null){
            anzahl = String.valueOf(anzahlAutos);
        }else{
            anzahl = "0";
        }

        Label Willkommen = new Label("<h1 style=\"text-align: center; color: #000000;\">Ihre eingetragenen Autos</h1>\n" +
                "<p>Hier können Sie Ihre veröffentlichen Autos einsehen</p>\n" +
                "<p>Um genauere Details einzusehen klicken Sie auf Mehr Informationen &nbsp;</p>\n" +
                "<p>Es wurden " + anzahl + " Autos, die von Ihnen veröffentlicht wurden, gefunden" + " &nbsp;</p>\n" +
                "<!-- Dieser kommentar ist nur im quelltext-editor sichtbar. -->\n" +
                "<p></p>", ContentMode.HTML);

        this.addComponent(Willkommen);
        this.setComponentAlignment(Willkommen, Alignment.MIDDLE_CENTER);

        Button zurürck = ComponentFactory.createButtonWithCaption("Zurück");

        Grid<Auto> ergSuche = getConfiguredGrid();
        ergSuche.setVisible(true);
        ergSuche.setEnabled(true);
        ergSuche.setSizeFull();
        ergSuche.setStyleGenerator(t -> "v-grid-cell-red");

        User currentUser = (User) UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER);
        List<Auto> list = AutoErstellenControl.getAllAutosWithVertrieblerID(currentUser);
        if(anzahlAutos != null){
            Notification.show("Achtung", "Es wurden " + list.size() +  " Autos gefunden", Notification.Type.WARNING_MESSAGE);
            if(list != null){
                ergSuche.setItems(list);
            }

        }else{
            Notification.show("Error", "Es wurden keine Autos gefunden!", Notification.Type.WARNING_MESSAGE);
        }

        zurürck.addClickListener(e -> UI.getCurrent().getNavigator().navigateTo(Views.MainView));

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.addComponent(zurürck);
        horizontalLayout.setComponentAlignment(zurürck, Alignment.MIDDLE_LEFT);

        this.addComponent(ergSuche);
        this.addComponent(horizontalLayout);
        this.setComponentAlignment(horizontalLayout,Alignment.MIDDLE_CENTER);

        setMargin(true);


    }

}
