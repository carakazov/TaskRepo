package com.task.api.dao;

import com.task.api.model.TakenItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ITakenItemDAO extends JpaRepository<TakenItem, Integer> {
    TakenItem getByTakerId(int takerId);
}
