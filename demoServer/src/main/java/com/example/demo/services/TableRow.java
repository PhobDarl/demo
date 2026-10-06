package com.example.demo.services;

public class TableRow {

    private String teamName;
            private int played, wins, draws, losses, points;

    public TableRow(String teamName){
        this.teamName = teamName;
    }


    public void addWin(){
        played++;
        wins++;
        points += 3;
    }

    public void addLoss(){
        played++;
        losses++;
    }

    public void addDraw(){
        played++;
        draws++;
        points++;
    }

    public String getTeamName(){
            return teamName;
        }

    public int getDraws() {
        return draws;
    }

    public int getLosses() {
        return losses;
    }

    public int getWins(){
        return wins;
    }

    public int getPlayed() {
        return played;
    }

    public int getPoints(){
        return points;
    }

}





