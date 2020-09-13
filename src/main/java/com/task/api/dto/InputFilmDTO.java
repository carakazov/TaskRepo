package com.task.api.dto;


//Класс для внесения данных по новому фильму в систему.
public class InputFilmDTO extends FilmDTO {
    private final int directorId;

    public InputFilmDTO(String title, int rating, int minAge,int directorId){
        super(title, rating, minAge);
        this.directorId = directorId;
    }

    public int getDirectorId(){
        return directorId;
    }
}
