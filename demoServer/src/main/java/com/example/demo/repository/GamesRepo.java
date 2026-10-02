package com.example.demo.repository;

import com.example.demo.entities.Games;
import com.example.demo.entities.Team;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GamesRepo extends JpaRepository<Games, Long> {

    List<Games> findAllByHomeTeam(String homeTeam);

    List<Games> findAllByAwayTeam(String awayTeam);
}