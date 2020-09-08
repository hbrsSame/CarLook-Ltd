package junit;

import control.LoginControl;
import control.RegisterControl;
import dto.UserLoginDTO;
import entity.User;
import exceptions.*;
import models.builder.LoginDTOBuilder;
import models.builder.UserBuilder;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import utils.Roles;

import static org.junit.Assert.assertTrue;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoginAndRegisterTest {
    User vertriebler;
    User endkunde;
    UserLoginDTO vertrieblerLogin;
    UserLoginDTO endkundenLogin;

    @Before
    public void setUpForEveryMethod(){
        endkunde = new UserBuilder(Roles.VERTRIEBLER)
                .mitUsername("thomas.look@carlook.de")
                .mitPassword("hallo123")
                .mitRepeadPassword("hallo123")
                .mitName("Thomas")
                .createUser();

        vertriebler = new UserBuilder(Roles.VERTRIEBLER)
                .mitUsername("hans.peter@carlook.de")
                .mitPassword("hallo123")
                .mitRepeadPassword("hallo123")
                .mitName("Hans")
                .createUser();

        vertrieblerLogin = new LoginDTOBuilder()
                .mitUsername(vertriebler.getUsername())
                .mitPassword(vertriebler.getPassword())
                .createDTO();

        endkundenLogin = new LoginDTOBuilder()
                .mitUsername(endkunde.getUsername())
                .mitPassword(endkunde.getPassword())
                .createDTO();
    }

    @Test
    public void A_registerUsers() throws NoUserFoundException, UserAlreadyExistException, RegisterException, RegexException {
        assertTrue(RegisterControl.registerUser(endkunde));
        assertTrue(RegisterControl.registerUser(vertriebler));
    }

    @Test
    public void B_TestLoginForVertriebler() throws DatabaseException, NoUserFoundException {
        assertTrue(LoginControl.checkAuthentication(endkundenLogin));
        assertTrue(LoginControl.checkAuthentication(vertrieblerLogin));
    }

    @Test
    public void C_DeleteUsers() throws NoUserFoundException {
        assertTrue(RegisterControl.DeleteUser(endkunde));
        assertTrue(RegisterControl.DeleteUser(vertriebler));
    }



}
