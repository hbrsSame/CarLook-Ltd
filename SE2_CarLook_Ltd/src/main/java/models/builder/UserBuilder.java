package models.builder;

import entity.Endkunde;
import entity.User;
import entity.Vertriebler;
import utils.Roles;

public class UserBuilder {

    private User user;
    private String type;

    public UserBuilder(String type){

        if(type.equals(Roles.VERTRIEBLER)) user = new Vertriebler();
        if(type.equals(Roles.ENDKUNDE)) user = new Endkunde();

        this.type = type;
    }

    public UserBuilder mitUsername(String username){
        this.user.setUsername(username);
        return this;
    }

    public UserBuilder mitPassword(String password){
        this.user.setPassword(password);
        return this;
    }

    public UserBuilder mitRepeadPassword(String password){
        this.user.setRepeatPassword(password);
        return this;
    }

    public UserBuilder mitID(int ID){
        this.user.setUserid(ID);
        return this;
    }

    public UserBuilder mitName(String name){
        if(this.type.equals(Roles.ENDKUNDE)){
            ((Endkunde) this.user).setName(name);
        }
        if(this.type.equals(Roles.VERTRIEBLER)){
            ((Vertriebler) this.user).setName(name);
        }
        return this;
    }

    public UserBuilder mitSpecificID(int ID){
        if(this.type.equals(Roles.ENDKUNDE)){
            ((Endkunde) this.user).setEndkundeID(ID);
        }
        if(this.type.equals(Roles.VERTRIEBLER)){
            ((Vertriebler) this.user).setVertrieblerID(ID);
        }
        return this;
    }

    public User createUser(){
        return this.user;
    }

}
