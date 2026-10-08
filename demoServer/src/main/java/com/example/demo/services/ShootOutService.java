package com.example.demo.services;

import com.example.demo.entities.ShootOut;
import com.example.demo.entities.Owner;
import com.example.demo.repository.GamesRepo;
import com.example.demo.repository.OwnerRepository;
import com.example.demo.repository.ShootOutRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShootOutService {

    GamesRepo gRepo;
    ShootOutRepository soRepo;

    public ShootOutService(GamesRepo gRepo, ShootOutRepository soRepo) {
        this.gRepo = gRepo;
        this.soRepo = soRepo;
    }

}