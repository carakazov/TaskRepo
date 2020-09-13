package com.task.api.service;

import com.task.api.business.exceptions.ItemNotFoundException;
import com.task.api.business.exceptions.handler.Validator;
import com.task.api.dao.IDirectorDAO;
import com.task.api.dao.IFilmDAO;
import com.task.api.dto.InputFilmDTO;
import com.task.api.model.Director;
import com.task.api.model.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilmService {
    private final IFilmDAO filmDAO;
    private final IDirectorDAO directorDAO;
    private final Validator catcher = new Validator();
    @Autowired
    public FilmService(IFilmDAO filmDAO, IDirectorDAO directorDAO){
        this.filmDAO = filmDAO;
        this.directorDAO = directorDAO;
        catcher.setDirectorDAO(directorDAO);
    }


    //Добавить новый диск.
    public void add(InputFilmDTO newFilm) throws ItemNotFoundException{
        catcher.checkInputFilm(newFilm);
        Director director = directorDAO.getOne(newFilm.getDirectorId());
        Film film = new Film(newFilm.getTitle(), newFilm.getRating(), newFilm.getMinAge(), director);
        filmDAO.save(film);
    }
}
