package com.task.api.dto;

//Класс для внесения в систему и получении из неё данных по режиссеру.
public class DirectorDataDTO {
    private final String name;
    private final String surname;

    public DirectorDataDTO(String name, String surname){
        this.name = name;
        this.surname = surname;
    }

    public String getName(){
        return name;
    }

    public String getSurname(){
        return surname;
    }
}
