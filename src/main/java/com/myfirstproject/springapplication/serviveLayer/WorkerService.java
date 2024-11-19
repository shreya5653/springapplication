package com.myfirstproject.springapplication.serviveLayer;

import com.myfirstproject.springapplication.entity.Worker;

import java.util.List;

public interface WorkerService {
    List<Worker> findAll();

    Worker findById(int theId);

    Worker save(Worker theWorker);

    void deleteById(int theId);
}
