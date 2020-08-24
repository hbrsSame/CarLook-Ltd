package models.factory;

import com.vaadin.ui.*;

public class ComponentFactory {

// Klasse für Erleichterung von erstellen verschiedener Komponenten

    public static TextField createTextFieldWithCaption(String caption){
        TextField result = new TextField();
        result.setCaption(caption);
        return result;

    }

    public static TextArea createTextAreaWithCaption(String caption){
        TextArea result = new TextArea();
        result.setCaption(caption);
        return result;
    }

    public static PasswordField createPasswordFieldWithCaption(String caption){
        PasswordField result = new PasswordField();
        result.setCaption(caption);
        return result;
    }

    public static Button createButtonWithCaption(String caption){
        Button result = new Button();
        result.setCaption(caption);
        return result;
    }


    public static CheckBox createCheckBoxWithCaption(String caption){
        CheckBox result = new CheckBox();
        result.setCaption(caption);
        return result;
    }

    public static TextField createTextFieldWithCaptionAndReadOnly(String caption, String value){
        TextField component =  new TextField(caption);
        component.setValue(value);
        component.setReadOnly(true);
        return component;
    }

    public static TextArea createTextAreaWithCaptionAndReadOnly(String caption, String value){
        TextArea component =  new TextArea(caption);
        component.setValue(value);
        component.setReadOnly(true);
        return component;
    }
}
