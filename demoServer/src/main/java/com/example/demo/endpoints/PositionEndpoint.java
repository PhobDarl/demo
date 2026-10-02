package com.example.demo.endpoints;

import com.example.demo.entities.Team;
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
        int homeTeamScore = request.getHomeGoals();
        int awayTeamScore = request.getAwayGoals();
        boolean done = gServ.recordGame(request);

        // need to calc winner and call a function to store in db here
        EnterScoresResponse resp = new EnterScoresResponse();
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
