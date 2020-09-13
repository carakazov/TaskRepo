package com.task.api.business.exceptions;

//Класс иключений, возникающих при попытке получить из базы несуществующий объект любого класса.
public class ItemNotFoundException extends RuntimeException{
    public ItemNotFoundException(String message){
        super(message);
    }
}
