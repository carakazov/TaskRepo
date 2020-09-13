package com.task.api.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "directors")
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @OneToMany(mappedBy = "director")
    private Set<Film> films;

    public Director(String name, String surname){
        this.name = name;
        this.surname = surname;
    }

    public Director(){}

    public String getName(){
        return name;
    }

    public String getSurname(){
        return surname;
    }

    public int getId(){
        return id;
    }

    public Set<Film> getFilms(){
        return films;
    }

    public void setFilms(Set<Film> films){
        this.films = films;
    }
}
