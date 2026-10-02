package com.example.demo;

import com.example.demo.entities.Team;
import com.example.demo.repository.teamsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EntityScan("com.example.demo.entities")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

    }

    // This function checks if the db has a list of the teams in the league and if not it adds them to the db
    @Bean
    CommandLineRunner seedTeams(teamsRepository repo) {
        return args -> {

            if (repo.count() > 0) return;

            List<String> names = List.of(
                    "Manchester City", "Tottenham", "Chelsea",
                    "London City Lionesses", "Liverpool", "Everton",
                    "Arsenal", "Crystal Palace", "Manchester United",
                    "West Ham", "Brighton", "Birmingham City",
                    "Aston Villa", "Charlton");

            List<Team> teamList = new ArrayList<>();
            for (String name : names) {
                Team team = new Team();
                team.setTeamName(name);
                teamList.add(team);
            }

            repo.saveAll(teamList);
        };
    }
}
