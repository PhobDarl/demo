package com.example.demo.repository;

import com.example.demo.entities.Owner;
import com.example.demo.entities.Team;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OwnerRepository extends JpaRepository<Owner, Long> {

    List<Owner> findByTeamId(Long id);

}