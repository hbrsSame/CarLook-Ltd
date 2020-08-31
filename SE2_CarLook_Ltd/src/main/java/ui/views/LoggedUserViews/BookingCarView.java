package ui.views.LoggedUserViews;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import control.BookingControl;
import control.SessionControl;
import entity.Auto;
import entity.Endkunde;
import entity.Reservierung;
import entity.User;
import exceptions.DatabaseException;
import exceptions.SessionException;
import models.factory.ComponentFactory;
import models.factory.ViewFactory;
import ui.panel.BottomPanel;
import utils.Roles;
import utils.Status;
import utils.Views;

public class BookingCarView extends VerticalLayout implements View {

    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent){
        try{
            SessionControl.controlLoggedSessionForEndkunde();

            this.addComponent(ViewFactory.createTopPanelForVerticalLayout() ) ;
            this.setUp();
            this.addComponent( new BottomPanel() );

        }catch (SessionException e ){
            Notification.show("Error", e.getMessage(),  Notification.Type.WARNING_MESSAGE);
        }
    }

    private void setUp(){

        User user = (User) UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER);
        Auto auto = (Auto) UI.getCurrent().getSession().getAttribute(Roles.SELECTED_CAR);

        Label Willkommen = new Label("<h1 style=\"text-align: center; color: #000000;\">Ein Auto reservieren</h1>\n" +
                "<p>Hier können Sie Ihre ein Auto reservieren</p>\n" +
                "<p>Füllen Sie dazu die notwendigen Felder aus und klicken Sie auf Reservieren! &nbsp;</p>\n" +
                "<p>Achtung! Wenn ein Auto reserviert wurde, kann diese Auto nicht gleichzeitig ein weitere Mal reserviert werden!" + " &nbsp;</p>\n" +
                "<p>Wenn Sie also das Auto nicht mehr gebrauchen, dann stornieren Sie Ihre Reservierungen oder geben es vorrzeitig zurück!" + " &nbsp;</p>\n" +
                "<!-- Dieser kommentar ist nur im quelltext-editor sichtbar. -->\n" +
                "<p></p>", ContentMode.HTML);

        this.addComponent(Willkommen);
        this.setComponentAlignment(Willkommen, Alignment.MIDDLE_CENTER);

        Panel bookingPanel = new Panel();
        TabSheet tabSheet = new TabSheet();

        VerticalLayout verticalLayout = new VerticalLayout();
        TextField name = ComponentFactory.createTextFieldWithCaption("Ihr Name");
        TextField vertriebler_name = ComponentFactory.createTextFieldWithCaption("Name des Vertriebler");
        TextField auto_marke = ComponentFactory.createTextFieldWithCaption("Ihr ausgewähltes Auto");
        TextField auto_baujahr = ComponentFactory.createTextFieldWithCaption("Baujahr des Autos");
        TextField dauer = ComponentFactory.createTextFieldWithCaption("Dauer der Reservierungen");

        Button book = ComponentFactory.createButtonWithCaption("Reservieren");

        if(auto != null) {

            name.setValue(((Endkunde) user).getName());
            name.setEnabled(false);

            vertriebler_name.setValue(auto.getVertriebler_name());
            vertriebler_name.setEnabled(false);

            auto_marke.setValue(auto.getMarke());
            auto_marke.setEnabled(false);

            auto_baujahr.setValue(auto.getBaujahr());
            auto_baujahr.setEnabled(false);

            book.addClickListener(e->{
                if(dauer.getValue().isEmpty()){
                    Notification.show("Error", "Das Feld Dauer darf nicht leer sein", Notification.Type.WARNING_MESSAGE);
                    return;
                }
                Reservierung reservierung = new Reservierung();
                reservierung.setAuto_id(auto.getAuto_id());
                reservierung.setEndkunden_id( ((Endkunde) user).getEndkundeID() );
                reservierung.setEndkunden_name(((Endkunde) user).getName());
                reservierung.setVertriebler_name(auto.getVertriebler_name());
                reservierung.setDauer(Integer.parseInt(dauer.getValue()));
                reservierung.setStatus(Status.ACTIVATED);
                reservierung.setMarke(auto.getMarke());
                try{
                    BookingControl.bookCar(reservierung);
                    Notification.show("Sucess", "Ihr Auto: " + auto.getMarke() + " wurde erfolgreich unter Ihrem Namen reserviert", Notification.Type.WARNING_MESSAGE);
                }catch( DatabaseException databaseException){
                    Notification.show("Error", databaseException.getMessage(), Notification.Type.WARNING_MESSAGE);
                }

                UI.getCurrent().getNavigator().navigateTo(Views.MainView);
            });

        }else{
            Notification.show("Es wurde kein Auto ausgewählt! Bitte ein freies Auto auswählen");
            UI.getCurrent().getNavigator().navigateTo(Views.MainView);
        }

        verticalLayout.addComponents(name, vertriebler_name, auto_marke, auto_baujahr, dauer);
        verticalLayout.setComponentAlignment(name, Alignment.MIDDLE_CENTER);
        verticalLayout.setComponentAlignment(vertriebler_name, Alignment.MIDDLE_CENTER);
        verticalLayout.setComponentAlignment(auto_marke, Alignment.MIDDLE_CENTER);
        verticalLayout.setComponentAlignment(auto_baujahr, Alignment.MIDDLE_CENTER);
        verticalLayout.setComponentAlignment(dauer, Alignment.MIDDLE_CENTER);

        tabSheet.addTab(verticalLayout, "Ausgewähltes Auto reservieren");
        tabSheet.setSizeUndefined();

        bookingPanel.setContent(tabSheet);
        this.addComponent(bookingPanel);
        this.setComponentAlignment(bookingPanel, Alignment.MIDDLE_CENTER);
        this.addComponent(book);
        this.setComponentAlignment(book, Alignment.MIDDLE_CENTER);
        bookingPanel.setSizeUndefined();

        setMargin(true);
    }
}
