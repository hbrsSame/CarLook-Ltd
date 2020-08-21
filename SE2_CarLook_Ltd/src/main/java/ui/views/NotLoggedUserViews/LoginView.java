package ui.views.NotLoggedUserViews;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;
import control.LoginControl;
import dao.LoginDAO;
import dto.UserLoginDTO;
import entity.User;
import entity.Vertriebler;
import ui.panel.BottomPanel;
import ui.panel.TopPanel;
import utils.Roles;
import utils.Views;

public class LoginView extends VerticalLayout implements View {

    public void enter(ViewChangeListener.ViewChangeEvent event){
        this.addComponent(new TopPanel());
        this.setUp();
        this.addComponent(new BottomPanel());
    }

    private void setUp(){

        LoginForm loginForm = new LoginForm();
        Button MainView = new Button("MainView");

        MainView.addClickListener(e->{
            UI.getCurrent().getNavigator().navigateTo(Views.MainView);
        });

        loginForm.addLoginListener(e->{
            UserLoginDTO currentUser = new UserLoginDTO();
            currentUser.setUsername(e.getLoginParameter("username"));
            currentUser.setPassword(e.getLoginParameter("password"));

            if(LoginControl.checkAuthentication(currentUser)){
                UI.getCurrent().getNavigator().navigateTo(Views.MainView);
            }
            else{
                Notification.show("Error", "Username oder Password falsch", Notification.Type.WARNING_MESSAGE);
            }
        });

        VerticalLayout coponentLayout = new VerticalLayout();
        coponentLayout.addComponents(loginForm, MainView);
        coponentLayout.setComponentAlignment(loginForm, Alignment.MIDDLE_CENTER);

        this.addComponent(coponentLayout);
    }

}
