package com.example.demo.endpoints;

import com.example.demo.generated.EnterScoresRequest;
import com.example.demo.generated.EnterScoresResponse;

import com.example.demo.services.GamesService;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


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


}
