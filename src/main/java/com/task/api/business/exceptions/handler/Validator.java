package com.task.api.business.exceptions.handler;

import com.task.api.business.Hasher;
import com.task.api.business.exceptions.DiskAlreadyTakenException;
import com.task.api.business.exceptions.IncorrectDateException;
import com.task.api.business.exceptions.ItemNotFoundException;
import com.task.api.business.exceptions.LoginAlreadyExistsException;
import com.task.api.dao.*;
import com.task.api.dto.*;

import java.util.Calendar;

//Класс для генерации исключений при подаче данных в систему.
public class Validator {
    private IUserDAO userDAO;
    private IDiskDAO diskDAO;
    private IFilmDAO filmDAO;
    private IDirectorDAO directorDAO;

    public void setUserDAO(IUserDAO userDAO){
        this.userDAO = userDAO;
    }

    public void setDiskDAO(IDiskDAO diskDAO){
        this.diskDAO = diskDAO;
    }

    public void setFilmDAO(IFilmDAO filmDAO){
        this.filmDAO = filmDAO;
    }

    public void setDirectorDAO(IDirectorDAO directorDAO) { this.directorDAO = directorDAO; }

    //Метод, генерирующий сообщение об ошибке. Используется в нескольких методах ниже.
    private String getErrorMessage(String itemType, int itemId){
        String itemNotFound = "Item with id = itemId does not exists";
        return itemNotFound.replaceAll("Item", itemType).replaceAll("itemId", Integer.toString(itemId));
    }

    //Проверка данного ID пользователя на существование.
    private void checkUserId(int userId){
        if(!userDAO.existsById(userId)){
            throw new ItemNotFoundException(getErrorMessage("User", userId));
        }
    }

    //Проверка данного ID фильма на существование.
    private void checkFilmId(int filmId){
        if(!filmDAO.existsById(filmId)){
            throw new ItemNotFoundException(getErrorMessage("Film", filmId));
        }
    }

    //Проверка данного ID диска на существование.
    private void checkDiskId(int diskId){
        if(!diskDAO.existsById(diskId)){
            throw new ItemNotFoundException(getErrorMessage("Disk", diskId));
        }
    }

    //Проверка любой, кроме даты возврата, данной даты на корректность.
    private void checkDate(Calendar date){
        if(Calendar.getInstance().before(date)){
            throw new IncorrectDateException("Input correct date");
        }
    }

    //Проверка данной даты возврата на корректность.
    private void checkReturnDate(Calendar date){
        if(!Calendar.getInstance().before(date)){
            throw new IncorrectDateException("Input correct date");
        }
    }

    //Проверка данного ID режиссера на существование.
    private void checkDirectorId(int directorId){
        if(!directorDAO.existsById(directorId)){
            throw new IncorrectDateException(getErrorMessage("Director", directorId));
        }
    }


    //Проверка состояния диска.
    private void checkDiskState(int diskId){
        if(diskDAO.getOne(diskId).isTaken()){
            throw new DiskAlreadyTakenException("Disk with id = " + diskId + " already taken");
        }
    }


    //Проверка логина пользователя при регистрации.
    private void checkUserRegistrationLogin(int login){
        if(userDAO.existsByLogin(login)){
            throw new LoginAlreadyExistsException("User with such login already exists");
        }
    }


    //Проверка логина пользователя при авторизации.
    private void checkUserLogin(int login){
        if(!userDAO.existsByLogin(login)){
            throw new ItemNotFoundException("Wrong login");
        }
    }

    //Проверка пароля пользователя при авторизации.
    private void checkUserPassword(int password){
        if(!userDAO.existsByPassword(password)){
            throw new ItemNotFoundException("Wrong password");
        }
    }

    //Проверка ID пользователя на существования.
    public void checkUserIdInput(int userId){
        checkUserId(userId);
    }

    //Проверка данных при записи фильмов на диск.
    public void checkDiskAndFilmsToRecordInput(DiskAndFilmsToRecordDTO diskAndFilms){
        checkDiskId(diskAndFilms.getDiskId());
        for(int filmId : diskAndFilms.getFilmIDs()){
            checkFilmId(filmId);
        }
    }

    //Проверка данных по передаче диска.
    public void checkDiskTransferInput(DiskTransferDTO diskTransfer){
        checkUserId(diskTransfer.getTakerId());
        checkDiskId(diskTransfer.getDiskId());
        checkDiskState(diskTransfer.getDiskId());
        checkReturnDate(diskTransfer.getReturnDate());
    }

    //Проверка данных при внесении нового фильма в базу.
    public void checkInputFilm(InputFilmDTO inputFilmDTO){
        checkDirectorId(inputFilmDTO.getDirectorId());
    }


    //Проверка данных по новому пользователю при регистрации.
    public void checkNewUser(InputUserDTO newUser){
        String login = newUser.getLoginAndPassword().getLogin();
        checkUserRegistrationLogin(Hasher.hashString(login));
        checkDate(newUser.getUserDTO().getBirthDate());
    }


    //Проверка данных пользователя при авторизации.
    public void checkAuthorizationUser(AuthorizationUserDTO userData){
        checkUserLogin(Hasher.hashString(userData.getLogin()));
        checkUserPassword(Hasher.hashString(userData.getPassword()));
    }
}
