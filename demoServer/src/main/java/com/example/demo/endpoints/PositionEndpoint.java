package com.example.demo.endpoints;

import com.example.demo.entities.Team;
import com.example.demo.entities.Games;
import com.example.demo.generated.*;

import com.example.demo.services.GamesService;
import com.example.demo.services.TeamsService;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;


@Endpoint
public class PositionEndpoint {

    private final GamesService gServ;

    public PositionEndpoint(GamesService gServ){
        this.gServ = gServ;
    }

    @PayloadRoot(namespace = "https://www.phobdarl.com/xml/football", localPart = "enterScoresRequest")
    @ResponsePayload
    public EnterScoresResponse setGame(@RequestPayload EnterScoresRequest request){

        int position;
        Games game = new Games();
        game.setHomeTeam(request.getHomeTeam());
        game.setAwayTeam(request.getAwayTeam());
        game.setHomeGoals(request.getHomeGoals());
        game.setAwayGoals(request.getAwayGoals());

        boolean exists = gServ.checkIfGameExists(game);
        System.out.println(exists);
        EnterScoresResponse resp = new EnterScoresResponse();
        boolean done = false;
        Games cleanGame = gServ.cleanGame(game);
        request.setHomeTeam(cleanGame.getHomeTeam());
        request.setAwayTeam(cleanGame.getAwayTeam());
        request.setHomeGoals(cleanGame.getHomeGoals());
        request.setAwayGoals(cleanGame.getHomeGoals());

        gServ.getLeagueTable();

        if (!exists){
            done = gServ.recordGame(request);
        }

        resp.setDone(done);
        return resp;





    }

    @PayloadRoot(namespace ="https://www.phobdarl.com/xml/football", localPart = "getTeamsRequest")
    @ResponsePayload
    public GetTeamsResponse sendTeamsToClient(@RequestPayload GetTeamsRequest request){
        String howmany = request.getHowMany();
        GetTeamsResponse resp = new GetTeamsResponse();

        if (howmany.equalsIgnoreCase("all")){
            List<Team> teams = TeamsService.getAllTeams();
            for (Team team : teams){
                TeamXSD teamX = new TeamXSD();
                teamX.setId(team.getId());
                teamX.setName(team.getTeamName());
                resp.getTeams().add(teamX);
            }
        }

        return resp;
    }


}
