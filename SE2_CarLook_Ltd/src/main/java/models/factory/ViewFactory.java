package models.factory;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.VerticalLayout;
import ui.panel.TopPanel;

public class ViewFactory {
    public static VerticalLayout createTopPanelForVerticalLayout(){
        VerticalLayout LayoutResult = new VerticalLayout();
        TopPanel topPanel = new TopPanel();
        LayoutResult.addComponent(topPanel);
        LayoutResult.setComponentAlignment(topPanel, Alignment.MIDDLE_CENTER);

        return LayoutResult;
    }
}
