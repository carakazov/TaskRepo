package com.task.api.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "films")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "rating")
    private int rating;

    @Column(name = "minage")
    private int minAge;

    @ManyToOne
    @JoinColumn(name = "directorid")
    private Director director;

    @ManyToMany
    @JoinTable(name = "filmondisk", joinColumns = @JoinColumn(name = "filmid"), inverseJoinColumns = @JoinColumn(name = "diskid"))
    private Set<Disk> disks;

    public Film(String title, int rating, int minAge, Director director){
        this.title = title;
        this.rating = rating;
        this.minAge = minAge;
        this.director = director;
    }

    public Film(){}

    public void setDisks(Set<Disk> disks){
        this.disks = disks;
    }

    public Set<Disk> getDisks(){
        return disks;
    }

    public int getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public int getRating(){
        return rating;
    }

    public int getMinAge(){
        return minAge;
    }

    public Director getDirector(){
        return director;
    }
}
