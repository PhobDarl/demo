package com.example.demo.entities;

import jakarta.persistence.*;

@Entity
@Table(name="shootOut")
public class ShootOut {
    @Id
    @Column(name = "gameId")
    private long gameId;

    @Column(name = "homeGoals")
    private int homeGoals;

    @Column(name = "awayGoals")
    private int awayGoals;

    public int getAwayGoals() {
        return awayGoals;
    }

    public int getHomeGoals() {
        return homeGoals;
    }

    public long getGameId() {
        return gameId;
    }

    public void setHomeGoals(int homeGoals) {
        this.homeGoals = homeGoals;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }


    public void setAwayGoals(int awayGoals) {
        this.awayGoals = awayGoals;
    }
}
