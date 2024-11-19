package com.myfirstproject.springapplication.controllers;

import com.myfirstproject.springapplication.entity.Worker;
import com.myfirstproject.springapplication.serviveLayer.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/workers")
public class WorkerController {

    private WorkerService workerService;

    @Autowired
    public WorkerController(WorkerService theWorkerService) {
        workerService = theWorkerService;
    }

    @GetMapping("/list")
    public List<Worker> listWorkers() {
        return workerService.findAll();
    }

    @PostMapping("/add")
    public Worker addWorker(@RequestBody Worker theWorker) {
        // Set ID to 0 to ensure the save creates a new worker instead of updating
        theWorker.setId(0);
        return workerService.save(theWorker);
    }

    @PutMapping("/update")
    public Worker updateWorker(@RequestBody Worker theWorker) {
        return workerService.save(theWorker);
    }

    @GetMapping("/{workerId}")
    public Worker getWorker(@PathVariable int workerId) {
        Worker theWorker = workerService.findById(workerId);
        if (theWorker == null) {
            throw new RuntimeException("Worker ID not found - " + workerId);
        }
        return theWorker;
    }

    @DeleteMapping("/delete/{workerId}")
    public String deleteWorker(@PathVariable int workerId) {
        Worker tempWorker = workerService.findById(workerId);

        if (tempWorker == null) {
            throw new RuntimeException("Worker ID not found - " + workerId);
        }

        workerService.deleteById(workerId);
        return "Deleted worker ID - " + workerId;
    }
}
