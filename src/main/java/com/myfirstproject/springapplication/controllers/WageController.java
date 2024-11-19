package com.myfirstproject.springapplication.controllers;

import com.myfirstproject.springapplication.entity.Wage;
import com.myfirstproject.springapplication.serviveLayer.WageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/wages")
public class WageController {

    @Autowired
    private WageService wageService;

    // Add a wage for a specific worker
    @PostMapping("/add/{workerId}")
    public ResponseEntity<Wage> addWage(@PathVariable Long workerId, @RequestBody Wage wage) {
        Wage savedWage = wageService.addWage(workerId, wage);
        return ResponseEntity.ok(savedWage);
    }

    @GetMapping("/worker/{workerId}")
    public ResponseEntity<List<Wage>> getWagesForWorker(@PathVariable Long workerId) {
        return ResponseEntity.ok(wageService.getWagesForWorker(workerId));
    }

    @DeleteMapping("/delete/{wageId}")
    public ResponseEntity<Void> deleteWage(@PathVariable Long wageId) {
        wageService.deleteWage(wageId);
        return ResponseEntity.noContent().build();
    }
}
