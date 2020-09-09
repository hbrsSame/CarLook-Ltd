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
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import utils.Roles;
import utils.Status;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CreateAutoAndBookTest {

    /* Vorgehen:

    In Methode A_registerUsers wird folgendes gemacht:
    1. Erstelle 2 User, einmal Endkunde und Vertriebler
    2. Regestriere diese beiden User
    3. Logge die User ein


    In der Methode B_createAutoAndBookTest wird folgendes gemacht:
    1. Erselle ein Auto im Namen des erstellten Vertriebler
    2. Reserviere das Auto im Namen des erstellten Endkunde
    3. Lösche beide User und die jeweils dazugehörigen Autos und Reservierungen
    4. Vergleiche das erstellte Auto mit dem Auto das in der Datenbank gespeichert werden!


     */

    private Auto auto;
    private Reservierung reservierung;
    private User vertriebler;
    private User endkunde;


    @Before
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

        //Logge Users ein und speichere die in den beiden User Objekte für spätere Zwecke
        assertTrue(LoginControl.checkAuthentication(vertrieblerLogin));
        this.vertriebler = LoginControl.getUser();
        assertTrue(LoginControl.checkAuthentication(endkundenLogin));
        this.endkunde = LoginControl.getUser();


    }


    @Test
    public void B_createCarAndBookTest() throws DatabaseException {

        //Erstelle ein Auto & ein Auto zum Vergleichen
        Auto autoFromDatabase  = null;
        auto = new AutoBuilder()
                .mitMarke("Audi A1")
                .mitBaujahr("2020")
                .mitBeschreibung("Sehr schönes Auto")
                .mitVertriebler_name( ((Vertriebler) this.vertriebler).getName())
                .mitVertriebler_id(((Vertriebler) this.vertriebler).getVertrieblerID())
                .createAuto();

        assertTrue(AutoErstellenControl.createAuto(vertriebler, auto));
        List<Auto> autoList = AutoErstellenControl.getAllAutosWithVertrieblerID(vertriebler);

        // Gebe das zuvor erstellte Auto zurück
        for(Auto auto : autoList){
            autoFromDatabase = auto;
        }

        // Erstelle eine Reserierung mi dem zuvor erzeugten Auto
        reservierung = new ReservierungBuilder()
                .mitDauer(5)
                .mitAuto_id(autoFromDatabase.getAuto_id())
                .mitEndkunden_id(((Endkunde) this.endkunde).getEndkundeID())
                .mitMarke(autoFromDatabase.getMarke())
                .mitEndkunden_name( ((Endkunde) endkunde).getName())
                .mitVertriebler_name( ((Vertriebler) vertriebler).getName())
                .createReservierung();


        assertTrue(BookingControl.bookCar(reservierung));

        // Vergleiche beide Autos miteinander
        assertEquals(auto.getMarke(), autoFromDatabase.getMarke() );
        assertEquals(auto.getBaujahr(), autoFromDatabase.getBaujahr() );
        assertEquals(auto.getBeschreibung(), autoFromDatabase.getBeschreibung() );
        assertEquals(auto.getVertriebler_name(), autoFromDatabase.getVertriebler_name() );
        assertEquals(auto.getStatus(), autoFromDatabase.getStatus() );
    }


    @Test
    public void D_DeleteUsedDataAndUsers() throws NoUserFoundException {

        //Lösche die Users und deren Daten
        assertTrue(RegisterControl.DeleteUser(endkunde));
        assertTrue(RegisterControl.DeleteUser(vertriebler));
    }

}
