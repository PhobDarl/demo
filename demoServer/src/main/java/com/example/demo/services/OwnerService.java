package com.example.demo.services;

import com.example.demo.entities.Team;
import com.example.demo.entities.Owner;
import com.example.demo.repository.GamesRepo;
import com.example.demo.repository.OwnerRepository;
import com.example.demo.repository.teamsRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OwnerService {

    teamsRepository teamRepo;
    OwnerRepository ownerRepo;

    public OwnerService(OwnerRepository ownerRepo, teamsRepository teamRepo){
        this.ownerRepo = ownerRepo;
        this.teamRepo = teamRepo;
    }

    // saving a new owner means making sure there is no other owner that is currently the owner, if there is set
    // end date to new owners start date
    public boolean saveNewOwner(Owner newOwner){
        List<Owner> owners= ownerRepo.findByTeamId(newOwner.getTeamId());

        if (owners.size() > 0){
            for (Owner oldOwner : owners){
                // find the current owner, end their contract
                String date = oldOwner.getEndDate();
                if (date.equals("none")){
                    oldOwner.setEndDate(newOwner.getStartDate());
                    ownerRepo.save(oldOwner);
                    break;
                }
            }
        }

        // save the updated old owner and the new owner
        ownerRepo.save(newOwner);


        return true;

    }

}
