package com.task.api.dto;

//Класс для данных по диску, который возвращается из системы для показа пользователю.
public class OutputFilmDTO extends FilmDTO {
    private final DirectorDataDTO director;
    public OutputFilmDTO(String title, int rating, int minAge, DirectorDataDTO director){
        super(title, rating, minAge);
        this.director = director;
    }
    public DirectorDataDTO getDirector(){
        return director;
    }
}
