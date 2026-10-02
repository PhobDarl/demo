package org.example;

import org.example.generated.*;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        // this contains all the information e.g. port needed to send a req
        FootballPortService serv = new FootballPortService();

        // ac port
        FootballPort port = serv.getFootballPortSoap11();

        // now we create the request #exciting
        EnterScoresRequest request = new EnterScoresRequest();

        List<TeamXSD> teams = getAllTeams(port);
        List<String> justNames = new ArrayList<String>();
        List<String> justIds = new ArrayList<String>();

        System.out.println("LIST OF TEAMS TO CHOOSE FROM:  ");
        for (TeamXSD team : teams){
            justNames.add(team.getName());
            justIds.add(String.valueOf(team.getId()));
            System.out.println(team.getId() + "     " + team.getName());
        }

        List<String> requestList = userInput(justNames);

        request.setHomeTeam(requestList.get(0));
        request.setAwayTeam(requestList.get(1));
        request.setHomeGoals(Integer.parseInt(requestList.get(2)));
        request.setAwayGoals(Integer.parseInt(requestList.get(3)));

        EnterScoresResponse response = port.enterScores(request);

        System.out.println(response.isDone());


    }

    public static List<String> userInput(List<String> justNames){
        List <String> detailsToSend = new ArrayList<String>();
        boolean valid = false;
        Scanner sc = new Scanner(System.in);
        while( !valid) {
            System.out.println("Enter the home team");
            String homeTeamInp = sc.nextLine();
            System.out.println("Enter the away team");
            String awayTeamInp = sc.nextLine();
            System.out.println("Enter the home goals");
            String homeGoalsInp = sc.nextLine();
            System.out.println("Enter the away goals");
            String awayGoalsInp = sc.nextLine();

            List<String> lowerNames = justNames.stream()
                    .map(String::toLowerCase)
                    .toList();

            valid = lowerNames.contains(homeTeamInp.trim().toLowerCase())
                    && lowerNames.contains(awayTeamInp.trim().toLowerCase());

            if (!valid){
                System.out.println("The team names you entered are not valid. Please try again.");
            } else {
                detailsToSend.add(homeTeamInp);
                detailsToSend.add(awayTeamInp);
                detailsToSend.add(homeGoalsInp);
                detailsToSend.add(awayGoalsInp);
            }
        }
        return detailsToSend;


    }


    public static List<TeamXSD> getAllTeams(FootballPort port){

        GetTeamsRequest req = new GetTeamsRequest();
        req.setHowMany("all");

        GetTeamsResponse resp = port.getTeams(req);
        return resp.getTeams();

    }
}