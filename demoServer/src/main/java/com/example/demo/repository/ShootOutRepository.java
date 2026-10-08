package com.example.demo.repository;

import com.example.demo.entities.ShootOut;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShootOutRepository extends JpaRepository<ShootOut, Long> {
    ShootOut findByGameId(Long gameId);

}