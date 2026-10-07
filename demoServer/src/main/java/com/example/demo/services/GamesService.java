package com.example.demo.services;

import com.example.demo.entities.Games;
import com.example.demo.entities.Team;
import com.example.demo.services.TableRow;
import com.example.demo.generated.EnterScoresRequest;
import com.example.demo.repository.GamesRepo;
import com.example.demo.repository.teamsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.LinkedHashMap;

@Service
public class GamesService {
    private final GamesRepo gamesRepo;
    private final teamsRepository teamRepo;
    private final TeamsService tServ;

    public GamesService(GamesRepo gamesRepo, teamsRepository teamRepo, TeamsService tServ){
        this.gamesRepo = gamesRepo;
        this.teamRepo = teamRepo;
        this.tServ = tServ;
    }


    // goal of this function is that when a team is entered by the user it removes all white space and ensure
    // capitalisation is the same as the team name already in the db.
    // mainly just for the strings because if ints werent ints itd have crashed already
    public Games cleanGame(Games dirtyGame){
        String dirtyHome = dirtyGame.getHomeTeam();
        String dirtyAway = dirtyGame.getAwayTeam();

        // trim first
        dirtyHome = dirtyHome.trim().replaceAll("\\s+", " ");
        dirtyAway = dirtyAway.trim().replaceAll("\\s+", " ");

        String cleanHome = "";
        String cleanAway = "";
        // Now caps
        List<Team> allTeams = tServ.getAllTeams();
        System.out.println(allTeams.size());
        for (Team t : allTeams){
            System.out.println(t.getTeamName());
            if (t.getTeamName().equalsIgnoreCase(dirtyHome)){
                cleanHome = t.getTeamName();
            } else if (t.getTeamName().equalsIgnoreCase(dirtyAway)){
                cleanAway = t.getTeamName();
            }
        }
        System.out.println(!(cleanAway.equals("")) && !(cleanHome.equals("")));
        System.out.println(cleanAway + "    " + cleanHome);
        if (!(cleanAway.equals("")) && !(cleanHome.equals(""))){
            dirtyGame.setHomeTeam(cleanHome);
            dirtyGame.setAwayTeam(cleanAway);
        }

        return dirtyGame;

    }
    public List<Games> getGamesByTeamName(String name){
        List<Games> gamesHome;
        List<Games> gamesAway;
        gamesHome = gamesRepo.findAllByHomeTeam(name);
        gamesAway = gamesRepo.findAllByAwayTeam(name);

        for ( Games game : gamesAway){
            gamesHome.add(game);
        }
        return gamesHome;
    }

    public boolean checkIfGameExists(Games gameToCheck){
        String homeTeam = gameToCheck.getHomeTeam();
        String awayTeam = gameToCheck.getAwayTeam();

        List<Games> allTeams = getGamesByTeamName(homeTeam);
        for (Games game : allTeams){
            System.out.println(game.getHomeTeam() + "   " + game.getAwayTeam());
            if (homeTeam.equals(game.getHomeTeam()) && awayTeam.equals(game.getAwayTeam())){
                return true;
            }
        }
        return false;
    }

    public List<Games> getGamesByTeam(Long id){

        // id gets passed in by browser so have that, find team name
        String teamName = teamRepo.getReferenceById(id).getTeamName();
        System.out.println(teamName);
        // find every game where team was home team or away team
        List<Games> homeTeamList = gamesRepo.findAllByHomeTeam(teamName);
        List<Games> awayTeamList = gamesRepo.findAllByAwayTeam(teamName);

        homeTeamList.addAll(awayTeamList);

        for (Games game : homeTeamList){
            System.out.println(game.getAwayGoals());
        }
        // return the combined list
        return homeTeamList;

    }

    // Creates the league table based on the matches played
    public List<TableRow> getLeagueTable(){
        // gets all teams and adds them to a map
        List<Team> allTeams = tServ.getAllTeams();
        // table row is just a class that contains a teams wins, losses e.t.c.
        Map<String, TableRow> rows = new HashMap<String, TableRow>();
        for (Team team : allTeams){
            TableRow row = new TableRow(team.getTeamName());
            rows.put(row.getTeamName(), row);
        }

        List<Games> allGames = gamesRepo.findAll();
        // updates the each class based on the team
        for (Games game : allGames){
            TableRow home = rows.get(game.getHomeTeam());
            TableRow away = rows.get(game.getAwayTeam());
            if (game.getHomeGoals() > game.getAwayGoals()){
                home.addWin();
                away.addLoss();
            } else if (game.getAwayGoals() > game.getHomeGoals()){
                away.addWin();
                home.addLoss();
            } else {
                home.addDraw();
                away.addDraw();
            }
        }

        List<TableRow> table = new ArrayList<>(rows.values());
        // sorts it so that the table is ordered by the points
        table.sort((a, b) -> b.getPoints() - a.getPoints());

        return table;
    }

    // saves the game to the database
    public boolean recordGame(EnterScoresRequest request){

        int homeGoals = request.getHomeGoals();
        int awayGoals = request.getAwayGoals();

        boolean draw = false;
        // arbitrary - never used but does calculate winner
        if (homeGoals-awayGoals < 0){
            String winner = request.getAwayTeam();
        } else if ( homeGoals - awayGoals > 0) {
            String winner = request.getHomeTeam();
        } else{
            draw = true;
        }

        try{
            // saves to database
            Games game = new Games();
            game.setAwayGoals(awayGoals);
            game.setHomeGoals(homeGoals);
            game.setHomeTeam(request.getHomeTeam());
            game.setAwayTeam(request.getAwayTeam());

            gamesRepo.save(game);

            return true;
        } catch (Exception e) {
            System.out.println("ERROR  " + e.toString());
            return false;
        }





    }
}
