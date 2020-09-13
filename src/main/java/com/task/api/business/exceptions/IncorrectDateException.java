package com.task.api.business.exceptions;

/*Класс исключений, возникающих при подаче в систему некорректной даты, например, при указании ещё на наступившей даты,
как дня рождения пользвателя.*/
public class IncorrectDateException extends RuntimeException{
    public IncorrectDateException(String message){
        super(message);
    }
}
