package com.example.demo.controllers;


import com.example.demo.services.GamesService;
import com.example.demo.entities.Games;
import com.example.demo.services.TeamsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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
    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/teams")
    public String teams(Model model){
        model.addAttribute("teams", teamsService.getAllTeams());

        return "teams";
    }

    @GetMapping("/teams/{id}")
    public String teams(@PathVariable long id, Model model){
        List<Games> games = gamesService.getGamesByTeam(id);
        // also need to display the winner
        Map<Long, String> winnerByGameId = new HashMap<Long, String>();
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
        model.addAttribute("games", games);
        model.addAttribute("winner", winnerByGameId);

        return "games";
    }
}
