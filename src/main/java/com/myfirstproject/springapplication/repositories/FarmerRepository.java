package com.myfirstproject.springapplication.repositories;

import com.myfirstproject.springapplication.entity.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FarmerRepository extends JpaRepository<Farmer, Long> {

}
