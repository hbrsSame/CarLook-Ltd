package control;

import dao.AutoDAO;
import entity.Auto;
import entity.User;

import java.util.ArrayList;
import java.util.List;

public class AutoErstellenControl {
    private static String errorMessages = "";

    public static boolean createAuto(User user, Auto auto){
        if(auto.getBaujahr().isEmpty()){
            auto.setBaujahr("0");
        }
        if(checkAutoData(auto)){
            return AutoDAO.getInstance().createAuto(user, auto);
        }

        return false;
    }

    public static List<Auto> getAllAutos(){
        List<Auto> list = AutoDAO.getInstance().getAllAutosFromDatabase();
        if(list != null){
            return list;
        }
        return null;
    }

    public static List<Auto> getAutoWithCriteria(String criteria){

        List<Auto> list = AutoDAO.getInstance().getAllAutosFromDatabase();
        List<Auto> listWithCriteria = new ArrayList<>();

        for ( Auto auto : list){
            String lowerCaseCriteria = convertString(criteria, criteria.length());
            String markeCase = convertString(auto.getMarke(), criteria.length());
            
            if(auto.getMarke().contains(criteria) | lowerCaseCriteria.contains(markeCase) | auto.getMarke().toLowerCase().contains(criteria.toLowerCase())){
                listWithCriteria.add(auto);
            }
        }
        return listWithCriteria;
    }

    private static boolean checkAutoData(Auto auto){
        boolean markeCheck = true;
        boolean baujahrCheck = true;
        boolean beschreibungCheck = true;

             if( auto.getMarke().isEmpty()        | auto.getMarke() == null){
                 markeCheck = false;
                 errorMessages += " Marke darf nicht leer sein";
             }
             if( auto.getBaujahr().isEmpty()      | auto.getBaujahr() == null | auto.getBaujahr().equals("0") ){
                 baujahrCheck = false;
                 errorMessages += " Baujahr darf nicht leer sein";
             }
             if( auto.getBeschreibung().isEmpty() | auto.getBeschreibung() == null) {
                 beschreibungCheck = false;
                 errorMessages += " Beschreibung darf nicht leer sein";
        }
        if( ! (markeCheck && baujahrCheck && beschreibungCheck ) ){
            errorMessages = "Es dürfen keine Felder leer sein! Alles sind Plfichtfelder!";
        }
        return markeCheck && baujahrCheck && beschreibungCheck;
    }

    public static String getMessage(){
        return errorMessages;
    }

    private static String convertString(String value, int lengthOfSearchWord)  {
        String criteriaWithFirstUpperCase = value.substring(0,1);
        return criteriaWithFirstUpperCase.toLowerCase() + value.substring(1,lengthOfSearchWord);
    }
}
