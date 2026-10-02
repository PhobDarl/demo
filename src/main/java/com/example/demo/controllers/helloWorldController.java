package com.example.demo.controllers;


import com.example.demo.services.GamesService;
import com.example.demo.services.TeamsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class helloWorldController {

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/teams")
    public String teams(Model model){
        model.addAttribute("teams", TeamsService.getAllTeams());

        return "teams";
    }

    @GetMapping("/teams/{id}")
    public String teams(@PathVariable long id, Model model){

        model.addAttribute("games", GamesService.getGamesByTeam(id));

        return "games";
    }
}
