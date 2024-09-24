package com.myfirstproject.springapplication.serviveLayer;

import com.myfirstproject.springapplication.entity.DailyWageWorker;
import com.myfirstproject.springapplication.repositories.DailyWageWorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DailyWageWorkerService {

    @Autowired
    private DailyWageWorkerRepository dailyWageWorkerRepository;

    // Get all workers
    public List<DailyWageWorker> getAllWorkers() {
        return dailyWageWorkerRepository.findAll();
    }

    // Get all workers by Farmer ID
    public List<DailyWageWorker> getWorkersByFarmerId(Long farmerId) {
        return dailyWageWorkerRepository.findByFarmerId(farmerId);
    }

    // Get worker by ID
    public Optional<DailyWageWorker> getWorkerById(Long id) {
        return dailyWageWorkerRepository.findById(id);
    }

    // Save or update worker
    public DailyWageWorker saveWorker(DailyWageWorker worker) {
        return dailyWageWorkerRepository.save(worker);
    }

    // Delete worker by ID
    public void deleteWorker(Long id) {
        dailyWageWorkerRepository.deleteById(id);
    }
}



//package com.myfirstproject.springapplication.serviveLayer;
//
//import com.myfirstproject.springapplication.entity.DailyWageWorker;
//import com.myfirstproject.springapplication.repositories.DailyWageWorkerRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class DailyWageWorkerService {
//
//    @Autowired
//    private DailyWageWorkerRepository dailyWageWorkerRepository;
//
//    // Get all workers
//    public List<DailyWageWorker> getAllWorkers() {
//        return dailyWageWorkerRepository.findAll();
//    }
//
//    // Get worker by ID
//    public Optional<DailyWageWorker> getWorkerById(Long id) {
//        return dailyWageWorkerRepository.findById(id);
//    }
//
//    // Save or update worker
//    public DailyWageWorker saveWorker(DailyWageWorker worker) {
//        return dailyWageWorkerRepository.save(worker);
//    }
//
//    // Delete worker by ID
//    public void deleteWorker(Long id) {
//        dailyWageWorkerRepository.deleteById(id);
//    }
//}
