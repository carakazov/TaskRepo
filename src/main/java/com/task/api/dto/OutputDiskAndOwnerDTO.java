package com.task.api.dto;

//Класс для возвращения из системы базовых данных по указанному диску и его хозяину.
public class OutputDiskAndOwnerDTO {
    private final OutputDiskDTO diskDTO;
    private final UserDTO owner;

    public OutputDiskAndOwnerDTO(OutputDiskDTO diskDTO, UserDTO owner){
        this.diskDTO = diskDTO;
        this.owner = owner;
    }

    public OutputDiskDTO getDiskDTO() {
        return diskDTO;
    }

    public UserDTO getOwner(){
        return owner;
    }
}
