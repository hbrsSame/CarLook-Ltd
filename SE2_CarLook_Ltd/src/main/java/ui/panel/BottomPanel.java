package ui.panel;

import com.vaadin.server.FileResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Image;
import com.vaadin.ui.VerticalLayout;
import utils.Path;

import java.io.File;

public class BottomPanel extends VerticalLayout {

    public BottomPanel(){
        FileResource logoResource = new FileResource( new File( Path.BASEPATH + "/BottumPanel.png"));
        Image logo = new Image(" ", logoResource);

        this.addComponent(logo);
        this.setComponentAlignment(logo, Alignment.TOP_CENTER);
    }
}
