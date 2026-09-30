package com.example.demo;

import com.example.demo.generated.EnterScoresRequest;
import com.example.demo.generated.EnteredScoresResponse;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


@Endpoint
public class PositionEndpoint {

    @PayloadRoot(namespace = "https://www.phobdarl.com/xml/football", localPart = "enterScoresRequest")
    @ResponsePayload
    public EnteredScoresResponse setGame(@RequestPayload EnterScoresRequest request){

        int position;
        int homeTeamScore = request.getHomeGoals();
        int awayTeamScore = request.getAwayGoals();

        EnteredScoresResponse resp = new EnteredScoresResponse();
        resp.setDone(true);
        
        return resp;
    }
}
