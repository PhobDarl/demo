package com.example.demo.repository;

import com.example.demo.entities.Games;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GamesRepo extends JpaRepository<Games, Long> {

}