package com.task.api.dto;

import java.util.Calendar;

//Класс для создания нового пользователя в базе данных.
public class InputUserDTO {
    private final AuthorizationUserDTO loginAndPassword;
    private final UserDTO userDTO;

    public InputUserDTO(String name, String surname, Calendar birthDate, String login, String password){
        this.userDTO = new UserDTO(name, surname, birthDate);
        this.loginAndPassword = new AuthorizationUserDTO(login, password);
    }

    public UserDTO getUserDTO(){
        return userDTO;
    }

    public AuthorizationUserDTO getLoginAndPassword(){
        return loginAndPassword;
    }
}
