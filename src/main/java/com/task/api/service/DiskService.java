package com.task.api.service;
import com.task.api.business.EntityToDTOTransactor;
import com.task.api.business.exceptions.handler.Validator;
import com.task.api.dao.IDiskDAO;
import com.task.api.dao.IFilmDAO;
import com.task.api.dao.ITakenItemDAO;
import com.task.api.dao.IUserDAO;
import com.task.api.dto.*;
import com.task.api.model.Disk;
import com.task.api.model.Film;
import com.task.api.model.TakenItem;
import com.task.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

@Service
public class DiskService {
    private final IDiskDAO diskDAO;
    private final IUserDAO userDAO;
    private final IFilmDAO filmDAO;
    private final ITakenItemDAO takenItemDAO;
    private final Validator catcher = new Validator();

    @Autowired
    public DiskService(IDiskDAO diskDAO, IUserDAO userDAO, IFilmDAO filmDAO, ITakenItemDAO takenItemDAO){
        this.diskDAO = diskDAO;
        this.userDAO = userDAO;
        this.filmDAO = filmDAO;
        this.takenItemDAO = takenItemDAO;
        catcher.setUserDAO(userDAO);
        catcher.setDiskDAO(diskDAO);
        catcher.setFilmDAO(filmDAO);
    }

    //Добавить новый диск пользователю, имеющему ID = ownerId.
    public String addDisk(int ownerId){
        catcher.checkUserIdInput(ownerId);
        User owner = userDAO.getOne(ownerId);
        Disk disk = new Disk(false, owner);
        owner.getDisks().add(disk);
        diskDAO.save(disk);
        userDAO.save(owner);
        return "Disk added to owner with id = " + ownerId;
    }

    //Получить все диски пользователя, имеющего ID = userId.
    public Set<OutputDiskDTO> getUserDisks(int userId){
        catcher.checkUserIdInput(userId);
        return EntityToDTOTransactor.getDisksInDTO(userDAO.getOne(userId).getDisks());
    }

    //Записывает указанные с помощью ID фильмы на диск, также указанный с помощью ID.
    public OutputDiskAndOwnerDTO recordFilmsOnDisk(DiskAndFilmsToRecordDTO filmsToRecord){
        catcher.checkDiskAndFilmsToRecordInput(filmsToRecord);
        int diskId = filmsToRecord.getDiskId();
        Disk disk = diskDAO.getOne(diskId);
        int[] filmIds = filmsToRecord.getFilmIDs();
        for(int id : filmIds){
            Film film = filmDAO.getOne(id);
            disk.getFilms().add(film);
            film.getDisks().add(disk);
            filmDAO.save(film);
        }
        diskDAO.save(disk);
        Set<OutputFilmDTO> filmsInDTO = EntityToDTOTransactor.getOutputFilmsInDTO(disk.getFilms());
        UserDTO owner = EntityToDTOTransactor.getOwnerInDTO(disk);
        OutputDiskDTO diskDTO = new OutputDiskDTO(diskId, disk.isTaken(), filmsInDTO);
        return new OutputDiskAndOwnerDTO(diskDTO, owner);
    }


    //Получить все свободные диски, учтенные в базе данных.
    public Set<OutputDiskAndOwnerDTO> getUntakenDisks(){
        Set<Disk> disks = diskDAO.getAllByTaken(false);
        return EntityToDTOTransactor.getDiskAndOwnersInDTO(disks);
    }

    //Получить все диски, взятые пользователем с ID = rakerId.
    public Set<OutputDiskAndOwnerDTO> getTakenDisksByUser(int takerId){
        catcher.checkUserIdInput(takerId);
        Set<OutputDiskAndOwnerDTO> disksInDTO = new HashSet<>();
        Set<TakenItem> takenItems = userDAO.getOne(takerId).getTakenItems();
        for(TakenItem takenItem : takenItems){
            disksInDTO.add(EntityToDTOTransactor.getDiskAndOwnerInDTO(takenItem.getDisk()));
        }
        return disksInDTO;
    }

    //Осуществляет передачу диска от одного пользователя к другому.
    public TakenDiskDTO takeDisk(DiskTransferDTO transferDTO){
        catcher.checkDiskTransferInput(transferDTO);
        int takerId = transferDTO.getTakerId();
        int diskId = transferDTO.getDiskId();
        Disk diskToTake = diskDAO.getOne(diskId);
        User taker = userDAO.getOne(takerId);
        Calendar currentDate = Calendar.getInstance();
        TakenItem takenItem = new TakenItem(diskToTake, taker, currentDate, transferDTO.getReturnDate());
        diskToTake.getTakenItems().add(takenItem);
        taker.getTakenItems().add(takenItem);
        diskToTake.setTaken(true);
        takenItemDAO.save(takenItem);
        diskDAO.save(diskToTake);
        userDAO.save(taker);
        OutputDiskAndOwnerDTO diskToTakeAndOwnerDTO = EntityToDTOTransactor.getDiskAndOwnerInDTO(diskToTake);
        UserDTO takerDTO = new UserDTO(taker.getName(), taker.getSurname(), taker.getBirthDate());
        return new TakenDiskDTO(diskToTakeAndOwnerDTO, takerDTO);
    }

    //Получить все диски, взятые у пользователя с ID = ownerId.
    public Set<TakenDiskDTO> getTakenDisksFromUser(int ownerId){
        catcher.checkUserIdInput(ownerId);
        Set<TakenDiskDTO> takenDisksDTO = new HashSet<>();
        Set<Disk> takenDisks = diskDAO.getAllByOwnerIdAndTaken(ownerId, true);
        for(Disk disk : takenDisks){
            OutputDiskAndOwnerDTO diskAndOwnerDTO = EntityToDTOTransactor.getDiskAndOwnerInDTO(disk);
            Set<TakenItem> takenItems = disk.getTakenItems();
            for(TakenItem takenItem : takenItems){
                if(Calendar.getInstance().before(takenItem.getReturnDate())){
                    User taker = takenItem.getTaker();
                    UserDTO takerDTO = new UserDTO(taker.getName(), taker.getSurname(), taker.getBirthDate());
                    TakenDiskDTO takenDiskDTO = new TakenDiskDTO(diskAndOwnerDTO, takerDTO);
                    takenDisksDTO.add(takenDiskDTO);
                    break;
                }
            }
        }
        return takenDisksDTO;
    }
}
