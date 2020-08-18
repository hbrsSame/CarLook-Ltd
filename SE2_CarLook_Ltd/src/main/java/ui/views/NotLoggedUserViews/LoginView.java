package ui.views.NotLoggedUserViews;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import utils.Views;

public class LoginView extends VerticalLayout implements View {

    public void enter(ViewChangeListener.ViewChangeEvent event){

        this.setUp();
    }

    private void setUp(){

        LoginForm loginForm = new LoginForm();
        Button MainView = new Button("MainView");

        MainView.addClickListener(e->{
            UI.getCurrent().getNavigator().navigateTo(Views.MainView);
        });

        loginForm.addLoginListener(e->{
            if(e.getLoginParameter("username").equals("admin") & e.getLoginParameter("password").equals("admin")){
                UI.getCurrent().getNavigator().navigateTo(Views.MainView);
            }else{
                Notification.show("Errror","Username oder Password falsch", Notification.Type.WARNING_MESSAGE);
            }

        });

        HorizontalLayout coponentLayout = new HorizontalLayout();
        coponentLayout.addComponents(loginForm, MainView);
        coponentLayout.setComponentAlignment(loginForm, Alignment.MIDDLE_CENTER);
        coponentLayout.setComponentAlignment(MainView,Alignment.TOP_RIGHT);

        this.addComponent(coponentLayout);
    }

}
