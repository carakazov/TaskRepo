package com.task.api.dao;

import com.task.api.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFilmDAO extends JpaRepository<Film, Integer> {
}
