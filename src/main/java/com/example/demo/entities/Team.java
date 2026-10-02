package com.example.demo.entities;

import jakarta.persistence.*;

@Entity
@Table(name="teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "team")
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
