package ui.views.LoggedUserViews;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
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
import utils.Views;

public class CreateAutoView extends VerticalLayout implements View {

    public void enter(ViewChangeListener.ViewChangeEvent event){
        try {
            SessionControl.controlLoggedSessionForVertriebler();
            this.addComponent( ViewFactory.createTopPanelForVerticalLayout() );
            this.setUp();
            this.addComponent( new BottomPanel() );
        } catch (SessionException e) {
            Notification.show("Error", e.getMessage(), Notification.Type.WARNING_MESSAGE);
        }


    }

    private void setUp(){
        //------------------Komponenten erstellen----------------------
        TextField marke = ComponentFactory.createTextFieldWithCaption("Marke");
        TextField baujahr = ComponentFactory.createTextFieldWithCaption("Baujahr");
        RichTextArea beschreibung = new RichTextArea("Beschreibung");
        Button autoErstellenButton = ComponentFactory.createButtonWithCaption("Jetzt Ihr Auto veröffentlichen");
        Button abbrechen = ComponentFactory.createButtonWithCaption("Abbrechen");
        Panel panelLayout = new Panel("Ein Auto veröffentlichen");
        VerticalLayout reihe1 = new VerticalLayout();
        VerticalLayout reihe2 = new VerticalLayout();

        //-------------------Komponent anpassen und initialiseren----------
        beschreibung.setHeight("500");
        beschreibung.setWidth("800");

        HorizontalLayout AutoErstellenLayout = new HorizontalLayout();
        HorizontalLayout buttonLayout = new HorizontalLayout();

        reihe1.addComponents(marke, baujahr);
        reihe2.addComponents(beschreibung);

        AutoErstellenLayout.addComponent(reihe1);
        AutoErstellenLayout.addComponent(reihe2);

        buttonLayout.addComponent(autoErstellenButton);
        buttonLayout.addComponent(abbrechen);

        //Dem finalen Layout alle erstellen und angepassten Komponent hinzufügen und anpassen---------------
        this.addComponent(panelLayout);
        panelLayout.setContent(AutoErstellenLayout);
        this.addComponent(buttonLayout);

        this.setComponentAlignment(panelLayout, Alignment.TOP_CENTER);
        this.setComponentAlignment(buttonLayout, Alignment.TOP_CENTER);
        panelLayout.setSizeUndefined();

        setSpacing(true);
        setMargin(true);

        autoErstellenButton.addClickListener(e->{
            User vertriebler = (User) UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER);

            if(vertriebler instanceof Vertriebler){
                Auto auto = new Auto();
                auto.setMarke(marke.getValue());
                auto.setBaujahr(baujahr.getValue());
                auto.setBeschreibung(beschreibung.getValue());
                auto.setVertriebler_id(((Vertriebler) vertriebler).getVertrieblerID());

                if(AutoErstellenControl.createAuto(vertriebler, auto) ) {
                    Notification.show("Sucesss", "Ihr Auto: " + marke.getValue()  +  " wurde erfolgreich eingetragen", Notification.Type.WARNING_MESSAGE);
                    UI.getCurrent().getNavigator().navigateTo(Views.MainView);
                }else{
                    Notification.show("Error", AutoErstellenControl.getMessage(), Notification.Type.WARNING_MESSAGE);
                }
            }
        });

        abbrechen.addClickListener(e->{
            UI.getCurrent().getNavigator().navigateTo(Views.MainView);
            Notification.show("Achtung!", "Kein Auto wurde eingetragen", Notification.Type.WARNING_MESSAGE);
        });



    }
}
