package com.myfirstproject.springapplication.repositories;

import com.myfirstproject.springapplication.entity.Wage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WageRepository extends JpaRepository<Wage, Long> {
    List<Wage> findByWorkerId(Long workerId);
}
