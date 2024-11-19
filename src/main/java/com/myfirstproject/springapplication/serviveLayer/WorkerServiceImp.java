package com.myfirstproject.springapplication.serviveLayer;

import com.myfirstproject.springapplication.repositories.WorkerRepository;
import com.myfirstproject.springapplication.entity.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerServiceImp implements WorkerService {

    private WorkerRepository workerRepository;

    @Autowired
    public WorkerServiceImp(WorkerRepository theWorkerRepository) {
        workerRepository = theWorkerRepository;
    }

    @Override
    public List<Worker> findAll() {
        return workerRepository.findAllByOrderByLastNameAsc();
    }

    @Override
    public Worker findById(int theId) {
        Optional<Worker> result = workerRepository.findById(theId);

        Worker theWorker;

        if (result.isPresent()) {
            theWorker = result.get();
        } else {
            throw new RuntimeException("Did not find worker ID - " + theId);
        }

        return theWorker;
    }

    @Override
    public Worker save(Worker theWorker) {
        return workerRepository.save(theWorker);
    }

    @Override
    public void deleteById(int theId) {
        workerRepository.deleteById(theId);
    }
}
