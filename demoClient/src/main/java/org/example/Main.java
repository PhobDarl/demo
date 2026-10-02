package org.example;

import org.example.generated.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        // this contains all the information e.g. port needed to send a req
        FootballPortService serv = new FootballPortService();

        // ac port
        FootballPort port = serv.getFootballPortSoap11();

        // now we create the request #exciting
        EnterScoresRequest request = new EnterScoresRequest();

        List<TeamXSD> teams = getAllTeams(port);

        System.out.println("LIST OF TEAMS TO CHOOSE FROM:  ");
        for (TeamXSD team : teams){
            System.out.println(team.getId() + "     " + team.getName());
        }


        request.setAwayGoals(3);
        request.setHomeGoals(5);
        request.setHomeTeam("Arsenal");
        request.setAwayTeam("Chelsea");

        EnterScoresResponse response = port.enterScores(request);

        System.out.println(response.isDone());


    }

    public static List<TeamXSD> getAllTeams(FootballPort port){

        GetTeamsRequest req = new GetTeamsRequest();
        req.setHowMany("all");

        GetTeamsResponse resp = port.getTeams(req);
        return resp.getTeams();

    }
}