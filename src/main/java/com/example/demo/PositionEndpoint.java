package com.example.demo;

import com.example.demo.generated.TeamNameRequest;
import com.example.demo.generated.TeamNameResponse;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


@Endpoint
public class PositionEndpoint {

    @PayloadRoot(namespace = "https://www.phobdarl.com/xml/football", localPart = "teamNameRequest")
    @ResponsePayload
    public TeamNameResponse getPosition(@RequestPayload TeamNameRequest request){

        int position;
        if (request.getTeamname().equalsIgnoreCase("tottenham")){
            position = 1;
        } else {
            position = 12;
        }

        TeamNameResponse resp = new TeamNameResponse();
        resp.setLeaguePosition(position);
        return resp;
    }
}
