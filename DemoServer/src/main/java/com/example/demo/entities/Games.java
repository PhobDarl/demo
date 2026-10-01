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
    private int homeGoals;

    @Column(name = "awayGoals")
    private int awayGoals;


    public long getGameId(){
        return gameId;
    }

    public  String getHomeTeam(){
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public int getAwayGoals() {
        return awayGoals;
    }

    public int getHomeGoals() {
        return homeGoals;
    }

    public void setAwayGoals(int awayGoals) {
        this.awayGoals = awayGoals;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public void setHomeGoals(int homeGoals) {
        this.homeGoals = homeGoals;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

}
