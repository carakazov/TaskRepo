package com.task.api.dto;

import java.util.Set;

//Класс для возврата базовых данных по диску - Id, состояние и диски, записанные на нем.
public class OutputDiskDTO {
    private final int diskId;
    private final boolean isTaken;
    private final Set<OutputFilmDTO> filmsOnDisk;

    public OutputDiskDTO(int diskId, boolean isTaken, Set<OutputFilmDTO> filmsOnDisk){
        this.diskId = diskId;
        this.isTaken = isTaken;
        this.filmsOnDisk = filmsOnDisk;
    }

    public boolean isTaken(){
        return isTaken;
    }

    public Set<OutputFilmDTO> getFilmsOnDisk() {
        return filmsOnDisk;
    }

    public int getDiskId() {
        return diskId;
    }
}
