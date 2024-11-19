package com.myfirstproject.springapplication.serviveLayer;

import com.myfirstproject.springapplication.entity.Wage;
import com.myfirstproject.springapplication.entity.Worker;
import com.myfirstproject.springapplication.repositories.WageRepository;
import com.myfirstproject.springapplication.repositories.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WageService {

    @Autowired
    private WageRepository wageRepository;

    @Autowired
    private WorkerRepository workerRepository;

    // Add wage for a worker
    public Wage addWage(Long workerId, Wage wage) {
        Worker worker = workerRepository.findById(Math.toIntExact(workerId))
                .orElseThrow(() -> new RuntimeException("Worker not found with ID: " + workerId));

        wage.setWorker(worker); // Link the wage to the worker
        return wageRepository.save(wage);
    }

    // Get wages for a specific worker
    public List<Wage> getWagesForWorker(Long workerId) {
        return wageRepository.findByWorkerId(workerId); // Custom query in repository
    }

    // Delete a wage by ID
    public void deleteWage(Long wageId) {
        wageRepository.deleteById(wageId);
    }
}
