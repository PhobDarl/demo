package com.example.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String teamName;

    public String getTeamName(){
        return teamName;
    }

    public long getId() {
        return id;
    }

    public void setTeamName(String newTeam){
        teamName = newTeam;
    }


}
