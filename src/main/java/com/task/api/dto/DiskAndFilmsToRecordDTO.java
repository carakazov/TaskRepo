package com.task.api.dto;


//Класс внесения соответствующих данных по записи фильмов на диск.
public class DiskAndFilmsToRecordDTO {
    private final int diskId;
    private final int[] filmIDs;

    public DiskAndFilmsToRecordDTO(int ownerId, int[] filmIDs){
        this.diskId = ownerId;
        this.filmIDs = filmIDs;
    }

    public int getDiskId(){
        return diskId;
    }

    public int[] getFilmIDs(){
        return filmIDs;
    }
}
