package com.task.api.dao;

import com.task.api.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDirectorDAO extends JpaRepository<Director, Integer> {
}
