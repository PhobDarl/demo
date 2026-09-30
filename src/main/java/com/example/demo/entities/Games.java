package com.example.demo.entities;

import jakarta.persistence.*;

@Entity
@Table(name="games")
public class Games {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gameId")
    private long gameId;

    @Column(name = "homeTeam")
    private String homeTeam;

    @Column(name = "awayTeam")
    private String awayTeam;

    @Column(name = "homeGoals")
    private String homeGoals;

    @Column(name = "awayGoals")
    private String awayGoals;


    public long getGameId(){
        return gameId;
    }

    public  String getHomeTeam(){
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public String getAwayGoals() {
        return awayGoals;
    }

    public String getHomeGoals() {
        return homeGoals;
    }

    public void setAwayGoals(String awayGoals) {
        this.awayGoals = awayGoals;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public void setHomeGoals(String homeGoals) {
        this.homeGoals = homeGoals;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

}
