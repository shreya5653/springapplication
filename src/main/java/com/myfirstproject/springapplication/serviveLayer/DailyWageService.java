package com.myfirstproject.springapplication.serviveLayer;

import com.myfirstproject.springapplication.entity.DailyWage;
import com.myfirstproject.springapplication.repositories.DailyWageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DailyWageService {

    @Autowired
    private DailyWageRepository dailyWageRepository;

    // Get all wages for a specific worker
    public List<DailyWage> getWagesByWorkerId(Long workerId) {
        return dailyWageRepository.findByWorkerId(workerId);
    }

    // Calculate the sum of wages for a specific worker
    public Double getTotalWagesByWorkerId(Long workerId) {
        return dailyWageRepository.sumWagesByWorkerId(workerId);
    }

    // Save or update a wage entry
    public DailyWage saveWage(DailyWage wage) {
        return dailyWageRepository.save(wage);
    }

    // Delete wage by worker ID and date
    public void deleteWageByWorkerIdAndDate(Long workerId, LocalDate date) {
        dailyWageRepository.deleteByWorkerIdAndDate(workerId, date);
    }

    // Delete wage by ID
    public void deleteWageById(Long id) {
        dailyWageRepository.deleteById(id);
    }
}