package com.example.demo.services;

import com.example.demo.generated.EnterScoresRequest;

public class CalcAndStoreWinner {

    public static boolean calculateWinner(EnterScoresRequest request){

        int homeGoals = request.getHomeGoals();
        int awayGoals = request.getAwayGoals();

        boolean draw = false;
        if (homeGoals-awayGoals < 0){
            String winner = request.getAwayTeam();
        } else if ( homeGoals - awayGoals > 0) {
            String winner = request.getHomeTeam();
        } else{
            draw = true;
        }



        return true;
    }
}
