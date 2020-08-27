package models.factory;

import com.vaadin.ui.Grid;
import entity.Auto;

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

        return grid;
    }
}
