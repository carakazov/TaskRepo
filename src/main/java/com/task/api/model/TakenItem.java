package com.task.api.model;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "takenitems")
public class TakenItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "diskid")
    private Disk disk;

    @ManyToOne
    @JoinColumn(name = "takerid")
    private User taker;

    @Column(name = "borrowdate")
    private Calendar borrowDate;

    @Column(name = "returndate")
    private Calendar returnDate;

    public TakenItem(Disk disk, User taker, Calendar borrowDate, Calendar returnDate){
        this.disk = disk;
        this.taker = taker;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public TakenItem(){}

    public int getId(){
        return id;
    }

    public User getTaker(){
        return taker;
    }

    public Disk getDisk(){
        return disk;
    }

    public Calendar getBorrowDate(){
        return  borrowDate;
    }

    public Calendar getReturnDate(){
        return returnDate;
    }
}

