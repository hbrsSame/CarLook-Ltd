package models.factory;

import com.vaadin.ui.Grid;
import entity.Auto;
import entity.Reservierung;

public class GridFactory {

    public static Grid<Auto> getConfiguredGrid(){
        Grid<Auto> grid = new Grid<>(Auto.class);
        grid.removeAllColumns();

        grid.addColumn(autosuche -> autosuche.getVertriebler_id())
                .setCaption("Vertriebler_ID").setId("Vertriebler_ID");
        grid.addColumn(autosuche -> autosuche.getVertriebler_name())
                .setCaption("Name").setId("Name");
        grid.addColumn(autosuche -> autosuche.getMarke() )
                .setCaption("Marke").setId("Marke");
        grid.addColumn(autosuche -> autosuche.getBaujahr() )
                .setCaption("Baujahr").setId("Baujahr");
        grid.addColumn(autosuche -> autosuche.getBeschreibung() )
                .setCaption("Beschreibung").setId("Beschreibung");
        grid.addColumn(autosuche -> autosuche.getStatus() )
                .setCaption("Verfügbarkeit").setId("Status_ID");

        return grid;
    }

    public static Grid<Reservierung> getConfiguredGridWithReservierungen(){
        Grid<Reservierung> grid = new Grid<>(Reservierung.class);
        grid.removeAllColumns();

        grid.addColumn(Reservierung::getReservierung_id)
                .setCaption("Reservierung_ID").setId("Reservierung_ID");
        grid.addColumn(Reservierung::getVertriebler_name)
                .setCaption("Vertriebler:Name").setId("Vertriebler:Name");
        grid.addColumn(Reservierung::getEndkunden_name)
                .setCaption("Endkunde:Name").setId("Endkunde:Name");
        grid.addColumn(Reservierung::getMarke )
                .setCaption("Auto:Name").setId("Auto:Name");
        grid.addColumn(Reservierung::getDauer)
                .setCaption("Dauer in Tagen").setId("Dauer");
        grid.addColumn(Reservierung::getStatus )
                .setCaption("Status").setId("Status");

        return grid;
    }


}
