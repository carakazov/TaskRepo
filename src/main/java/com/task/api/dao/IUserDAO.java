package com.task.api.dao;

import com.task.api.business.exceptions.ItemNotFoundException;
import com.task.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserDAO extends JpaRepository<User, Integer> {
    boolean existsByLogin(int login);
    boolean existsByPassword(int password);
    User getByLogin(int login);
}
