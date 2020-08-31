package models.service;

import exceptions.RegexException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexChecker {

    public static boolean checkEmail(String string2check)throws RegexException {
        Pattern pattern = Pattern.compile(RegexPattern.EMAIL__PATTERN);
        Matcher matcher = pattern.matcher(string2check);
        if(matcher.matches()){
            return true;
        }else{
            throw new RegexException("Die Subdomain muss @carlook.de sein. Bitte korrigieren Sie Ihre Eingabe");
        }
    }
}
