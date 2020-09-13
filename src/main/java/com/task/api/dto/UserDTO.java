package com.task.api.dto;

import java.util.Calendar;


//Класс для базовых данных по пользователю.
public class UserDTO {
    private final String name;
    private final String surname;
    private final Calendar birthDate;

    public UserDTO(String name, String surname, Calendar birthDate){
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
    }

    public String getName(){
        return name;
    }

    public String getSurname(){
        return surname;
    }

    public Calendar getBirthDate(){
        return birthDate;
    }
}
