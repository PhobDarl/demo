package com.example.demo.repository;

import com.example.demo.entities.Team;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface teamsRepository extends JpaRepository<Team, Long> {
    Team findByTeamName(String teamName);

}
