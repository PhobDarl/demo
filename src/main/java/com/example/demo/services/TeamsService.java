package com.example.demo.services;

import com.example.demo.entities.Team;
import com.example.demo.repository.GamesRepo;
import com.example.demo.repository.teamsRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TeamsService {

    GamesRepo gamesRepo;
    static teamsRepository teamsRepository;

    public TeamsService(GamesRepo gamesRepo, teamsRepository teamsRepository){
        this.gamesRepo = gamesRepo;
        this.teamsRepository = teamsRepository;
    }

    public static List<Team> getAllTeams(){
        return teamsRepository.findAll();
    }
}
