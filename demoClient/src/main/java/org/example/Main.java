package org.example;

import org.example.generated.*;

import java.util.List;
import java.util.Scanner;

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
        // should convert TeamsXSD into a key value pair so can easily check if teams exist
        // could also give user ooption to enter by id or team name
        // thisll do for now
        //
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the home team");
        String homeTeamInp = sc.nextLine();
        System.out.println("Enter the away team");
        String awayTeamInp = sc.nextLine();
        System.out.println("Enter the home goals");
        int homeGoalsInp = Integer.parseInt(sc.nextLine());
        System.out.println("Enter the away goals");
        int awayGoalsInp = Integer.parseInt(sc.nextLine());




        request.setAwayGoals(awayGoalsInp);
        request.setHomeGoals(homeGoalsInp);
        request.setHomeTeam(homeTeamInp);
        request.setAwayTeam(awayTeamInp);

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