package com.task.api.dto;

//Класс для внесения данных для авторизации.
public class AuthorizationUserDTO {
    private final String login;
    private final String password;

    public AuthorizationUserDTO(String login, String password){
        this.login = login;
        this.password = password;
    }

    public String getLogin(){
        return login;
    }

    public String getPassword(){
        return password;
    }
}
