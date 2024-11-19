package com.myfirstproject.springapplication.repositories;

import com.myfirstproject.springapplication.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkerRepository extends JpaRepository<Worker, Integer> {
    List<Worker> findAllByOrderByLastNameAsc();
}
