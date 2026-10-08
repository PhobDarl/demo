package com.example.demo.endpoints;

import com.example.demo.entities.Team;
import com.example.demo.entities.Games;
import com.example.demo.entities.Owner;
import com.example.demo.entities.ShootOut;
import com.example.demo.generated.*;

import com.example.demo.services.GamesService;
import com.example.demo.services.TeamsService;
import com.example.demo.services.OwnerService;


import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;


@Endpoint
public class PositionEndpoint {

    private final GamesService gServ;
    private final OwnerService oServ;

    public PositionEndpoint(GamesService gServ, OwnerService oServ){
        this.gServ = gServ;
        this.oServ = oServ;
    }

    @PayloadRoot(namespace = "https://www.phobdarl.com/xml/football", localPart = "enterScoresRequest")
    @ResponsePayload

    // Endpoint for a user entering a game
    public EnterScoresResponse setGame(@RequestPayload EnterScoresRequest request){

        int position;
        Games game = new Games();
        // extracts the data from the request
        game.setHomeTeam(request.getHomeTeam());
        game.setAwayTeam(request.getAwayTeam());
        game.setHomeGoals(request.getHomeGoals());
        game.setAwayGoals(request.getAwayGoals());

        // cleans the game so it is in the right format for the database ( and checks)
        Games cleanGame = gServ.cleanGame(game);
        // boolean flag so same game is not entered twice
        boolean exists = gServ.checkIfGameExists(cleanGame);
        System.out.println(exists);
        EnterScoresResponse resp = new EnterScoresResponse();
        boolean done = false;

        request.setHomeTeam(cleanGame.getHomeTeam());
        request.setAwayTeam(cleanGame.getAwayTeam());
        request.setHomeGoals(cleanGame.getHomeGoals());
        request.setAwayGoals(cleanGame.getAwayGoals());

        gServ.getLeagueTable();

        //commits to database if it is not a duplicate game
        if (!exists){
            // sends back flag to say whether its been done or not
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

    @PayloadRoot(namespace ="https://www.phobdarl.com/xml/football", localPart = "enterOwnerRequest")
    @ResponsePayload
    public EnterOwnerResponse addNewOwner(@RequestPayload EnterOwnerRequest request){
        Owner newOwner = new Owner();
        newOwner.setTeamId(request.getTeamId());
        newOwner.setOwnerName(request.getOwnerName());
        newOwner.setStartDate(request.getStartDate());
        newOwner.setEndDate(request.getEndDate());

        boolean done = oServ.saveNewOwner(newOwner);

        EnterOwnerResponse resp = new EnterOwnerResponse();
        resp.setDone(done);
        return resp;
    }

    @PayloadRoot(namespace ="https://www.phobdarl.com/xml/football", localPart = "enterShootOutRequest")
    @ResponsePayload
    public EnterShootOutResponse enterShootOut(@RequestPayload EnterShootOutRequest request){
        ShootOut so = new ShootOut();
        so.setGameId(request.getGameId());
        so.setHomeGoals(request.getHomeGoals());
        so.setAwayGoals(request.getAwayGoals());

        EnterShootOutResponse resp = new EnterShootOutResponse();
        resp.setDone(true);

        return resp;
    }


}
