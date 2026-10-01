package com.example.demo.services;

import com.example.demo.entities.Games;
import com.example.demo.generated.EnterScoresRequest;
import com.example.demo.repository.GamesRepo;
import com.example.demo.repository.teamsRepository;
import org.springframework.stereotype.Service;

@Service
public class GamesService {
    private final GamesRepo gamesRepo;
    private final teamsRepository teamRepo;

    public GamesService(GamesRepo gamesRepo, teamsRepository teamRepo){
        this.gamesRepo = gamesRepo;
        this.teamRepo = teamRepo;
    }

    public static boolean recordGame(EnterScoresRequest request){

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

        try{
            Games game = new Games();
            game.setAwayGoals(awayGoals);
            game.setHomeGoals(homeGoals);
            game.setHomeTeam(request.getHomeTeam());
            game.setAwayTeam(request.getAwayTeam());
            return true;
        } catch (Exception e) {
            System.out.println("ERROR  " + e.toString());
            return false;
        }





    }
}
