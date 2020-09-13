package com.task.api.dao;

import com.task.api.model.Disk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface IDiskDAO extends JpaRepository<Disk, Integer> {
    Set<Disk> getAllByTaken(boolean isTaken);
    Set<Disk> getAllByOwnerIdAndTaken(int ownerId, boolean isTaken);
}
