package org.example;

import org.example.generated.*;

import java.util.List;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
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
        Map<String, Long> idFromName = new HashMap<String, Long>();
        for (TeamXSD team : teams) {
            justNames.add(team.getName());
            justIds.add(String.valueOf(team.getId()));
            idFromName.put(team.getName(), team.getId());
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

            } else if( decision == 3){
                System.out.println("In Progress");
                Map<String, String> input = ownerUserInput(justNames);
                Long teamId = idFromName.get(input.get("OwnedTeamName"));

                EnterOwnerRequest ownerRequest = new EnterOwnerRequest();

                ownerRequest.setOwnerName(input.get("OwnerName"));
                ownerRequest.setTeamId(teamId);
                ownerRequest.setStartDate(input.get("StartMonth"));
                ownerRequest.setEndDate("none");

                EnterOwnerResponse resp = port.enterOwner(ownerRequest);


            } else {
                // quit
                System.out.println("Bye Bye");
                break;
            }
        }
    }

    public static Map<String, String> ownerUserInput(List<String> justNames){
        List<String> months = List.of("Jan", "Feb", "Mar", "April", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");
        Scanner sc = new Scanner(System.in);
        Map<String, String> ownerReq = new HashMap<String, String>();
        EnterOwnerRequest ownerRequest = new EnterOwnerRequest();
        boolean valid = false;
        while(!valid) {
            System.out.println("Enter the owner name");
            String ownersName = sc.nextLine().trim();
            System.out.println("Enter the team they own");
            String ownedTeamName = sc.nextLine().trim();
            List<String> lowerNames = justNames.stream()
                    .map(String::toLowerCase)
                    .toList();

            valid = lowerNames.contains(ownedTeamName.trim().toLowerCase());
            System.out.println("Enter the starting month in format: (e.g. Jun, May, Oct)");
            String startMonth = sc.nextLine();
            valid = valid && months.contains(startMonth);


            if (valid){
                ownerReq.put("OwnerName", ownersName);
                ownerReq.put("OwnedTeamName", ownedTeamName);
                ownerReq.put("StartMonth", startMonth);
                return ownerReq;

            } else {
                System.out.println("Team didnt exist or invalid month, try again: \n");
            }
        }
        return ownerReq;









    }


    public static int menuPage() {
        // just displays options and takes choice and does some error catching
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\nWelcome to the world's best Football Games Display and Inputter");
            System.out.println("Please choose from the following options:");
            System.out.println("1. Show list of teams\n2. Input a new game\n3. Assign a new Owner \n4. Quit");
            try {
                int decision = Integer.parseInt(sc.nextLine().trim());
                if (decision >= 1 && decision <= 4) {
                    return decision;
                }
                System.out.println("Please pick 1, 2, 3 or 4.");
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
        boolean areInt = false;
        Scanner sc = new Scanner(System.in);
        while( !valid || !areInt) {
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


            try {
                int homeGoals = Integer.parseInt(homeGoalsInp);
                int awayGoals = Integer.parseInt(awayGoalsInp);
                areInt = true;

            } catch (Exception e) {
                System.out.println( e.toString());
            }

            if (!valid){
                System.out.println("The team names you entered are not valid. Please try again.");
            } else if (!areInt){
                System.out.println("Please enter integers e.g. 4 NOT four");
            }else {
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