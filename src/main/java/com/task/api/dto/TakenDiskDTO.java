package com.task.api.dto;

//Класс для показа полных данных по диску - состояние диска и Id, фильмы на нем, хозяин и взявший в аренду пользователь.
public class TakenDiskDTO {
    private final OutputDiskAndOwnerDTO diskData;
    private final UserDTO taker;
    public TakenDiskDTO(OutputDiskAndOwnerDTO diskData, UserDTO taker){
        this.diskData = diskData;
        this.taker = taker;
    }

    public OutputDiskAndOwnerDTO getDiskData() {
        return diskData;
    }

    public UserDTO getTaker() {
        return taker;
    }
}
