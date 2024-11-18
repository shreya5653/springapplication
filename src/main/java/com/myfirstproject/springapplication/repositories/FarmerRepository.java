package com.myfirstproject.springapplication.repositories;

import com.myfirstproject.springapplication.entity.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FarmerRepository extends JpaRepository<Farmer, Long> {
    Optional<Farmer> findByEmail(String email);
    Optional<Farmer> findByUsernameAndPassword(String username, String password);
    Optional<Farmer> findByUsername(String username);
}