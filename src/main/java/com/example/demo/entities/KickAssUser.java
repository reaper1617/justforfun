package com.example.demo.entities;


import javax.persistence.*;

@Entity
@Table(name = "users",schema = "kickassusers",catalog = "")
public class KickAssUser {

    public KickAssUser() {
    }

    public KickAssUser(String name, int numOfKicks) {
        this.name = name;
        this.numOfKicks = numOfKicks;
    }

    @Column(name = "id",nullable = false,unique = true)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;


    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "num_of_kicks")
    private int numOfKicks;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfKicks() {
        return numOfKicks;
    }

    public void setNumOfKicks(int numOfKicks) {
        this.numOfKicks = numOfKicks;
    }
}
