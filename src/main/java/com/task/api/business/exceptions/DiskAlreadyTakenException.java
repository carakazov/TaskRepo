package com.task.api.business.exceptions;

//Класс исключений, возникаемых при попытке осуществить передачу уже взятого в аренду диска.
public class DiskAlreadyTakenException extends RuntimeException{
    public DiskAlreadyTakenException(String message){
        super(message);
    }
}
