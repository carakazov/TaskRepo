package com.task.api.dto;

//Абстрактный класс с базовыми данными по фильму, которые будут использованы в классах-наследниках.
public abstract class FilmDTO {
    protected final String title;
    protected final int rating;
    protected final int minAge;

    public FilmDTO(String title, int rating, int minAge){
        this.title = title;
        this.rating = rating;
        this.minAge = minAge;
    }

    public String getTitle(){
        return title;
    }

    public int getRating(){
        return rating;
    }

    public int getMinAge(){
        return minAge;
    }
}


