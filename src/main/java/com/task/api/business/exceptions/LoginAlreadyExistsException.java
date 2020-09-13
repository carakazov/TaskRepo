package com.task.api.business.exceptions;

//Класс исключений, возникающих при попытке зарегестрировать пользователя под логином, уже существующим в базе.
public class LoginAlreadyExistsException extends RuntimeException{
    public LoginAlreadyExistsException(String message){
        super(message);
    }
}
