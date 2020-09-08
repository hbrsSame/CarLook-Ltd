package junit;

import control.AutoErstellenControl;
import control.BookingControl;
import control.LoginControl;
import control.RegisterControl;
import dto.UserLoginDTO;
import entity.*;
import exceptions.*;
import models.builder.AutoBuilder;
import models.builder.LoginDTOBuilder;
import models.builder.ReservierungBuilder;
import models.builder.UserBuilder;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import utils.Roles;

import static org.junit.Assert.assertTrue;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CreateAutoAndBookTest {

    private Auto auto;
    private Reservierung reservierung;
    private User vertriebler;
    private User endkunde;


    @Test
    public void A_registerUsers() throws NoUserFoundException, UserAlreadyExistException, RegisterException, RegexException, DatabaseException {
        // Register Users
        endkunde = new UserBuilder(Roles.ENDKUNDE)
                .mitName("Hans")
                .mitUsername("hans.peter@carlook.de")
                .mitPassword("hallo123")
                .mitRepeadPassword("hallo123")
                .createUser();

        vertriebler = new UserBuilder(Roles.VERTRIEBLER)
                .mitName("Peter")
                .mitUsername("peter.hans@carlook.de")
                .mitPassword("hallo123")
                .mitRepeadPassword("hallo123")
                .createUser();

        RegisterControl.DeleteUser(endkunde);
        RegisterControl.DeleteUser(vertriebler);

        assertTrue(RegisterControl.registerUser(endkunde));
        assertTrue(RegisterControl.registerUser(vertriebler));


        //Log Users
        UserLoginDTO vertrieblerLogin = new LoginDTOBuilder()
                .mitUsername(vertriebler.getUsername())
                .mitPassword(vertriebler.getPassword())
                .createDTO();

        UserLoginDTO endkundenLogin = new LoginDTOBuilder()
                .mitUsername(endkunde.getUsername())
                .mitPassword(endkunde.getPassword())
                .createDTO();

        assertTrue(LoginControl.checkAuthentication(vertrieblerLogin));
        this.vertriebler = LoginControl.getUser();
        assertTrue(LoginControl.checkAuthentication(endkundenLogin));
        this.endkunde = LoginControl.getUser();


    }


    @Test
    public void C_createCarAndRegisterToDatabase(){
        auto = new AutoBuilder()
                .mitMarke("Audi A1")
                .mitBaujahr("2020")
                .mitBeschreibung("Sehr schönes Auto")
                .mitVertriebler_name("Hans")
                .createAuto();

        assertTrue(AutoErstellenControl.createAuto(vertriebler, auto));
        this.auto = AutoErstellenControl.getAuto();
    }

    @Test
    public void D_bookCarAndRegistoToDatabase() throws DatabaseException {
        reservierung = new ReservierungBuilder()
                .mitDauer(5)
                .mitAuto_id(auto.getAuto_id())
                .mitEndkunden_id(endkunde.getUserid())
                .mitMarke(auto.getMarke())
                .mitEndkunden_name( ((Endkunde) endkunde).getName())
                .mitVertriebler_name( ((Vertriebler) vertriebler).getName())
                .createReservierung();

        assertTrue(BookingControl.bookCar(reservierung));
    }

    @Test
    public void E_DeleteUsedDataAndUsers() throws NoUserFoundException {
        assertTrue(RegisterControl.DeleteUser(endkunde));
        assertTrue(RegisterControl.DeleteUser(vertriebler));
    }

}
