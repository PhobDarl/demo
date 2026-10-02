package com.example.demo.services;

import com.example.demo.entities.Games;
import com.example.demo.entities.Team;
import com.example.demo.generated.EnterScoresRequest;
import com.example.demo.repository.GamesRepo;
import com.example.demo.repository.teamsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GamesService {
    private final GamesRepo gamesRepo;
    private final teamsRepository teamRepo;

    public GamesService(GamesRepo gamesRepo, teamsRepository teamRepo){
        this.gamesRepo = gamesRepo;
        this.teamRepo = teamRepo;
    }

    public static List<Games> getGamesByTeam(Long id){
        // id gets passed in by browser so have that, find team name
        String teamName = teamRepo.getReferenceById(id).getTeamName();

        // find every game where team was home team
        List<Games> homeTeamList = gamesRepo.findAllByHomeTeam(teamName);
        List<Games> awayTeamList = gamesRepo.findAllByAwayTeam(teamName);

         homeTeamList.addAll(awayTeamList);

         return homeTeamList;

    }

    public boolean recordGame(EnterScoresRequest request){

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

            gamesRepo.save(game);

            return true;
        } catch (Exception e) {
            System.out.println("ERROR  " + e.toString());
            return false;
        }





    }
}
