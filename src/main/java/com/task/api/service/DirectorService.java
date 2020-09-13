package com.task.api.service;

import com.task.api.dao.IDirectorDAO;
import com.task.api.dto.DirectorDataDTO;
import com.task.api.model.Director;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DirectorService {
    private final IDirectorDAO directorDAO;

    @Autowired
    public DirectorService(IDirectorDAO directorDAO){
        this.directorDAO = directorDAO;
    }

    //Добавить нового режиссера.
    public void add(DirectorDataDTO newDirector){
        Director director = new Director(newDirector.getName(), newDirector.getSurname());
        directorDAO.save(director);
    }
}
