package ui.views.NotLoggedUserViews;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import sun.rmi.runtime.Log;
import utils.Views;

public class MainView extends VerticalLayout implements View {

    public void enter(ViewChangeListener.ViewChangeEvent event){

        this.setUp();

    }

    private void setUp(){
        Button welcome = new Button("Willkommen");
        welcome.setCaption("Willkommen");

        Button LoginView = new Button("LoginView");

        LoginView.addClickListener(e->{
            UI.getCurrent().getNavigator().navigateTo(Views.LoginView);
        });

        TextField FirstTextField = new TextField();
        FirstTextField.setCaption("FirstTextField");

        VerticalLayout componentLayout = new VerticalLayout();
        componentLayout.addComponents(FirstTextField, welcome, LoginView);
        componentLayout.setComponentAlignment(FirstTextField, Alignment.TOP_CENTER);
        componentLayout.setComponentAlignment(welcome, Alignment.TOP_CENTER);
        componentLayout.setComponentAlignment(LoginView, Alignment.MIDDLE_CENTER);

        this.addComponent(componentLayout);
        this.setSizeFull();
        setMargin(true);
    }

}
