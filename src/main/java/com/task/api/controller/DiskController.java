package com.task.api.controller;

import com.task.api.dto.*;
import com.task.api.service.DiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class DiskController {
    private final DiskService diskService;

    @Autowired
    public DiskController(DiskService diskService){
        this.diskService = diskService;
    }

    //Добавить диск пользователю с ID = ownerId.
    @PostMapping(value = "/addDisk/{ownerId}")
    public ResponseEntity<?> addDiskToUser(@PathVariable(value = "ownerId") int ownerId){
        String result = diskService.addDisk(ownerId);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }


    //Записать фильмы на диск.
    @PostMapping(value = "/disks/recordFilms")
    public ResponseEntity<OutputDiskAndOwnerDTO> recordFilms(@RequestBody DiskAndFilmsToRecordDTO diskAndFilms){
        OutputDiskAndOwnerDTO outputDisk = diskService.recordFilmsOnDisk(diskAndFilms);
        return new ResponseEntity<>(outputDisk, HttpStatus.ACCEPTED);
    }


    //Получить все диски пользователя с ID = userId.
    @GetMapping(value = "/users/{userId}/disks")
    public ResponseEntity<Set<OutputDiskDTO>> getUserDisks(@PathVariable(value = "userId") int userId){
        Set<OutputDiskDTO> disks = diskService.getUserDisks(userId);
        return new ResponseEntity<>(disks, HttpStatus.OK);
    }


    //Получить список всех свободных дисков.
    @GetMapping(value = "/disks/untaken")
    public ResponseEntity<?> getUntaken(){
        Set<OutputDiskAndOwnerDTO> resultSet = diskService.getUntakenDisks();
        return new ResponseEntity<>(resultSet, HttpStatus.OK);
    }


    //Получить все диски, взятые пользователем с ID = takerId.
    @GetMapping(value = "/disks/takenBy/{takerId}")
    public ResponseEntity<?> getTakenDisks(@PathVariable(value = "takerId") int takerId){
        Set<OutputDiskAndOwnerDTO> disks = diskService.getTakenDisksByUser(takerId);
        return new ResponseEntity<>(disks, HttpStatus.OK);
    }


    //Осуществить передачу диска.
    @PostMapping(value = "/disks/take")
    public ResponseEntity<TakenDiskDTO> takeDisk(@RequestBody DiskTransferDTO takeDisk){
        TakenDiskDTO takenDisk = diskService.takeDisk(takeDisk);
        return new ResponseEntity<>(takenDisk, HttpStatus.ACCEPTED);
    }


    //Получить все диски, взятые у пользователя с ID = ownerId.
    @GetMapping(value = "/disks/takenFrom/{ownerId}")
    public ResponseEntity<?> takenDisksFrom(@PathVariable(value = "ownerId") int ownerId){
        Set<TakenDiskDTO> takenDisks = diskService.getTakenDisksFromUser(ownerId);
        return new ResponseEntity<>(takenDisks, HttpStatus.OK);
    }

}
