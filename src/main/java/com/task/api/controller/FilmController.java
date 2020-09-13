package com.task.api.controller;

import com.task.api.business.exceptions.ItemNotFoundException;
import com.task.api.dto.InputFilmDTO;
import com.task.api.model.Film;
import com.task.api.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilmController {
    private final FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService){
        this.filmService = filmService;
    }


    //Добавить новый фильм.
    @PostMapping(value = "/films")
    public ResponseEntity<?> addFilm(@RequestBody InputFilmDTO newFilm) throws ItemNotFoundException {
        filmService.add(newFilm);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
