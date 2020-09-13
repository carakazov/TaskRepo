package com.task.api.dto;

import java.util.Calendar;

//Класс для данных по передаче диска от одного пользователя к другому.
public class DiskTransferDTO {
    private final int diskId;
    private final int takerId;
    private final Calendar returnDate;

    public DiskTransferDTO(int diskId, int takerId, Calendar returnDate){
        this.diskId = diskId;
        this.takerId = takerId;
        this.returnDate = returnDate;
    }

    public int getDiskId(){
        return diskId;
    }

    public int getTakerId(){
        return takerId;
    }

    public Calendar getReturnDate() {
        return returnDate;
    }
}
