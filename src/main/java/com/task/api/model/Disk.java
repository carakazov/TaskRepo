package com.task.api.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "disks")
public class Disk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "taken")
    private boolean taken;

    @ManyToOne
    @JoinColumn(name = "ownerid")
    private User owner;

    @OneToMany(mappedBy = "disk")
    private Set<TakenItem> takenItems;

    @ManyToMany(mappedBy = "disks")
    private Set<Film> films;

    public Disk(boolean taken, User owner){
        this.taken = taken;
        this.owner = owner;
    }

    public void setFilms(Set<Film> films){
        this.films = films;
    }

    public Set<Film> getFilms(){
        return films;
    }

    public void setTaken(boolean isTaken){
        this.taken = isTaken;
    }

    public boolean isTaken() {
        return taken;
    }

    public void setTakenItems(Set<TakenItem> takenItems){
        this.takenItems = takenItems;
    }

    public Set<TakenItem> getTakenItems(){
        return takenItems;
    }

    public Disk(){}

    public int getId(){
        return id;
    }

    public User getOwner(){
        return owner;
    }
}
