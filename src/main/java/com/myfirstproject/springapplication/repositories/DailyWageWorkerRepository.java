package com.myfirstproject.springapplication.repositories;

import com.myfirstproject.springapplication.entity.DailyWageWorker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DailyWageWorkerRepository extends JpaRepository<DailyWageWorker,Long> {
    List<DailyWageWorker> findByFarmerId(Long farmerId);
}
