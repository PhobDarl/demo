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

        // get all the teams and populate a list for later use
        List<TeamXSD> teams = fetchTeams(port);
        List<String> justNames = new ArrayList<String>();
        List<String> justIds = new ArrayList<String>();
        for (TeamXSD team : teams) {
            justNames.add(team.getName());
            justIds.add(String.valueOf(team.getId()));
        }

        while(true) {
            int decision = menuPage();

            // 1 means show teams
            if (decision == 1){
                displayTeams(justNames, justIds);
                // send a request
            } else if (decision == 2){
                EnterScoresRequest request = new EnterScoresRequest();

                List<String> requestList = userInput(justNames);

                request.setHomeTeam(requestList.get(0));
                request.setAwayTeam(requestList.get(1));
                request.setHomeGoals(Integer.parseInt(requestList.get(2)));
                request.setAwayGoals(Integer.parseInt(requestList.get(3)));

                EnterScoresResponse response = port.enterScores(request);

                System.out.println(response.isDone());

            } else {
                // quit
                System.out.println("Bye Bye");
                break;
            }
        }
    }

    public static int menuPage() {
        // just displays options and takes choice and does some error catching
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\nWelcome to the world's best Football Games Display and Inputter");
            System.out.println("Please choose from the following options:");
            System.out.println("1. Show list of teams\n2. Input a new game\n3. Quit");
            try {
                int decision = Integer.parseInt(sc.nextLine().trim());
                if (decision >= 1 && decision <= 3) {
                    return decision;
                }
                System.out.println("Please pick 1, 2 or 3.");
            } catch (NumberFormatException e) {
                System.out.println("Enter a number, try again");
            }
        }
    }
    // displays the teams
    public static void displayTeams (List<String> justNames, List<String> justIds ){
        System.out.println("LIST OF TEAMS TO CHOOSE FROM:  ");
        for (int i = 0 ; i < justNames.size() ; i ++) {
            System.out.println(justIds.get(i) + "     " + justNames.get(i));
        }
    }


    // takes user input for entering a match
    public static List<String> userInput(List<String> justNames){
        List <String> detailsToSend = new ArrayList<String>();
        boolean valid = false;
        Scanner sc = new Scanner(System.in);
        while( !valid) {
            System.out.println("Enter the home team");
            String homeTeamInp = sc.nextLine().trim();
            System.out.println("Enter the away team");
            String awayTeamInp = sc.nextLine().trim();
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
    // gets all the teams from the server
    public static List<TeamXSD> fetchTeams( FootballPort port){
            GetTeamsRequest req = new GetTeamsRequest();
            req.setHowMany("all");

            GetTeamsResponse resp = port.getTeams(req);
            return resp.getTeams();

        }

}