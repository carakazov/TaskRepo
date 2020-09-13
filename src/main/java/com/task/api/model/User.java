package com.task.api.model;

import org.hibernate.annotations.CollectionId;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "login")
    private int login;

    @Column(name = "password")
    private int password;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "birthdate")
    private Calendar birthDate;

    @OneToMany(mappedBy = "owner")
    private Set<Disk> disks;

    @OneToMany(mappedBy = "taker")
    private Set<TakenItem> takenItems;

    public User(int login, int password, String name, String surname, Calendar birthDate){
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
    }

    public User(){}

    public void setTakenItems(Set<TakenItem> takenItems){
        this.takenItems = takenItems;
    }

    public Set<TakenItem> getTakenItems(){
        return takenItems;
    }

    public void setDisks(Set<Disk> disks){
        this.disks = disks;
    }

    public Set<Disk> getDisks(){
        return disks;
    }

    public int getId(){
        return id;
    }

    public int getLogin(){
        return login;
    }

    public int getPassword(){
        return password;
    }

    public String getName(){
        return name;
    }

    public String getSurname(){
        return surname;
    }

    public Calendar getBirthDate(){
        return birthDate;
    }
}
