package com.task.api.controller;

import com.task.api.dto.DirectorDataDTO;
import com.task.api.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DirectorController {
    private final DirectorService directorService;

    @Autowired
    public DirectorController(DirectorService directorService){
        this.directorService = directorService;
    }

    //Добавить режиссера.
    @PostMapping(value = "/directors")
    public ResponseEntity<?> addDirector(@RequestBody DirectorDataDTO newDirector){
        directorService.add(newDirector);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
