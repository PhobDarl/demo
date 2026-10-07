package com.example.demo.controllers;


import com.example.demo.services.GamesService;
import com.example.demo.entities.Games;
import com.example.demo.services.TeamsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.services.TableRow;

import java.util.Map;
import java.util.HashMap;
import java.util.List;


@Controller
public class helloWorldController {

    private final GamesService gamesService;
    private final TeamsService teamsService;

    public helloWorldController(GamesService gamesService, TeamsService teamsService){
        this.gamesService = gamesService;
        this.teamsService = teamsService;
    }
    // home page
    @GetMapping("/")
    public String home(Model model){
        // display league table
        List<TableRow> table = gamesService.getLeagueTable();
        model.addAttribute("leagueTable", table);

        return "home";
    }


    // team page
    @GetMapping("/teams")
    public String teams(Model model){
        //displays teams
        model.addAttribute("teams", teamsService.getAllTeams());

        return "teams";
    }
    // page for each team showing games
    @GetMapping("/teams/{id}")
    public String teams(@PathVariable long id, Model model){
        // get all the games for team based on the id
        List<Games> games = gamesService.getGamesByTeam(id);
        Map<Long, String> winnerByGameId = new HashMap<Long, String>();
        // calculates the winner / loss / draw
        for (Games game : games){
            Long gameId = game.getGameId();
            if (game.getHomeGoals() < game.getAwayGoals()){
                winnerByGameId.put(gameId, game.getAwayTeam());
            } else if (game.getHomeGoals() > game.getAwayGoals()){
                winnerByGameId.put(gameId, game.getHomeTeam());
            } else{
                winnerByGameId.put(gameId, "Draw");
            }

        }
        // displays game table and winner
        model.addAttribute("games", games);
        model.addAttribute("winner", winnerByGameId);

        return "games";
    }
}
