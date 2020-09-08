package models.builder;

import dto.UserLoginDTO;

public class LoginDTOBuilder {

    private UserLoginDTO userLoginDTO;

    public LoginDTOBuilder(){ userLoginDTO = new UserLoginDTO(); }

    public LoginDTOBuilder mitUsername(String username){
        this.userLoginDTO.setUsername(username);
        return this;
    }

    public LoginDTOBuilder mitPassword(String password){
        this.userLoginDTO.setPassword(password);
        return this;
    }


    public UserLoginDTO createDTO(){
        return this.userLoginDTO;
    }

}
