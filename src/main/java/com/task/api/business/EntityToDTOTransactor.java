package com.task.api.business;

import com.task.api.dto.*;
import com.task.api.model.Disk;
import com.task.api.model.Film;
import com.task.api.model.User;

import java.util.HashSet;
import java.util.Set;

//Класс с методами для генерации DTO - объектов основе сущностей.
public class EntityToDTOTransactor {
    //На основе сета сущностей Film генерируется сет объектов класса OutputFilmDTO.
    public static Set<OutputFilmDTO> getOutputFilmsInDTO(Set<Film> films){
        Set<OutputFilmDTO> filmsInDTO = new HashSet<>();
        for(Film film : films){
            DirectorDataDTO directorDTO = new DirectorDataDTO(film.getDirector().getName(), film.getDirector().getSurname());
            OutputFilmDTO filmInDTO = new OutputFilmDTO(film.getTitle(), film.getRating(), film.getMinAge(), directorDTO);
            filmsInDTO.add(filmInDTO);
        }
        return filmsInDTO;
    }

    //На основе сета сущностей Disk генерируется сет объектов класса OutputDiskAndOwnerDTO.
    public static Set<OutputDiskAndOwnerDTO> getDiskAndOwnersInDTO(Set<Disk> disks){
        Set<OutputDiskAndOwnerDTO> diskAndOwnerDTOS = new HashSet<>();
        for(Disk disk : disks){
            OutputDiskAndOwnerDTO diskAndOwnerDTO = getDiskAndOwnerInDTO(disk);
            diskAndOwnerDTOS.add(diskAndOwnerDTO);
        }
        return diskAndOwnerDTOS;
    }

    //На основе сущности Disk генерируется объект класса OutputDiskAndOwnerDTO.
    public static OutputDiskAndOwnerDTO getDiskAndOwnerInDTO(Disk disk){
        OutputDiskDTO outputDiskDTO = getDiskInDTO(disk);
        UserDTO owner = getOwnerInDTO(disk);
        return new OutputDiskAndOwnerDTO(outputDiskDTO, owner);
    }

    //На основе сущности Disk генерируется объект класса OutputDiskDTO.
    public static OutputDiskDTO getDiskInDTO(Disk disk){
        Set<OutputFilmDTO> films = getOutputFilmsInDTO(disk.getFilms());
        return new OutputDiskDTO(disk.getId(), disk.isTaken(), films);
    }

    //На основе сета сущностей Disk генерируется сет объектов класса OutputDiskDTO.
    public static Set<OutputDiskDTO> getDisksInDTO(Set<Disk> disks){
        Set<OutputDiskDTO> disksInDTO = new HashSet<>();
        for(Disk disk : disks){
            OutputDiskDTO diskInDTO = getDiskInDTO(disk);
            disksInDTO.add(diskInDTO);
        }
        return disksInDTO;
    }


    //На основе сущности Disk генериурется объект класса UserDTO.
    public static UserDTO getOwnerInDTO(Disk disk){
        User owner = disk.getOwner();
        return new UserDTO(owner.getName(), owner.getSurname(), owner.getBirthDate());
    }

}
