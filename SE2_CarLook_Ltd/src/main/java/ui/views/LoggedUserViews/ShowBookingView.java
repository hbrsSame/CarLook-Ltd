package ui.views.LoggedUserViews;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import control.BookingControl;
import control.SessionControl;
import entity.Auto;
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

import static models.factory.GridFactory.getConfiguredGridWithReservierungen;
@PreserveOnRefresh
public class ShowBookingView extends VerticalLayout implements View {

    private Reservierung reservierung = null;

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

        Label Willkommen = new Label("<h1 style=\"text-align: center; color: #000000;\">Ihre reservierten Autos</h1>\n" +
                "<p>Hier können Sie Ihre Autos, die von Ihnen reserviert wurden, einsehen oder die Reservierung stornieren</p>\n" +
                "<p>Klicken Sie dazu ein von Ihnen reserviertes Auto an und wählen Sie stornieren aus! &nbsp;</p>\n" +
                "<p>Wenn ein Auto storinert wurde, kann dieses Auto wieder reserviert werden." + " &nbsp;</p>\n" +
                "<p>Ein Auto kann nur von einer Person gleichzeitig reserviert werden" + " &nbsp;</p>\n" +
                "<!-- Dieser kommentar ist nur im quelltext-editor sichtbar. -->\n" +
                "<p></p>", ContentMode.HTML);

        this.addComponent(Willkommen);
        this.setComponentAlignment(Willkommen, Alignment.MIDDLE_CENTER);

        Grid<Reservierung> alleReservierungen = getConfiguredGridWithReservierungen();
        alleReservierungen.setVisible(true);
        alleReservierungen.setEnabled(true);
        alleReservierungen.setSizeFull();
        alleReservierungen.setItems(BookingControl.getBookingList(user));

        HorizontalLayout horizontalLayout = new HorizontalLayout();

        Button stornieren = ComponentFactory.createButtonWithCaption("Stornieren");
        Button zuruck = ComponentFactory.createButtonWithCaption("Zürück zur Startseite");

        alleReservierungen.setStyleGenerator(t -> "v-grid-cell-red");

        alleReservierungen.addItemClickListener(reservierungItemClick-> {
            reservierung = reservierungItemClick.getItem();
            if(reservierung.getStatus().equals(Status.CANCELED)){
                stornieren.setEnabled(false);
            }else{
                stornieren.setEnabled(true);
            }
        });

        stornieren.addClickListener(e ->{
            if(reservierung != null){
                try{
                    BookingControl.cancelBooking(reservierung);
                    UI.getCurrent().getNavigator().navigateTo(Views.ShowBookingView);
                    Notification.show("Sucess", "Ihre Reservierung wurde storniert und das Auto wieder freigegeben", Notification.Type.WARNING_MESSAGE);

                }catch (DatabaseException exception){
                    UI.getCurrent().getNavigator().navigateTo(Views.ShowBookingView);
                    Notification.show("Error", exception.getMessage(), Notification.Type.WARNING_MESSAGE);
                }
            }else{
                Notification.show("Error", "Keine Reservierung ausgewählt", Notification.Type.WARNING_MESSAGE);
            }

        });


        horizontalLayout.addComponent(stornieren);
        horizontalLayout.addComponent(zuruck);
        horizontalLayout.setComponentAlignment(stornieren, Alignment.MIDDLE_RIGHT);
        horizontalLayout.setComponentAlignment(zuruck, Alignment.MIDDLE_LEFT);

        this.addComponent(alleReservierungen);
        this.addComponent(horizontalLayout);
        this.setComponentAlignment(horizontalLayout,Alignment.MIDDLE_CENTER);

        setMargin(true);

    }


}
