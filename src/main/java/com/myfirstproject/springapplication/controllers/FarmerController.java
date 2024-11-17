package com.myfirstproject.springapplication.controllers;

import com.myfirstproject.springapplication.entity.Farmer;
import com.myfirstproject.springapplication.serviveLayer.FarmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/farmer")
public class FarmerController {

    @Autowired
    private FarmerService farmerService;

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody Farmer farmer) {
        try {
            Farmer savedFarmer = farmerService.register(farmer);

            // Return the farmer's profile as the response
            return ResponseEntity.status(HttpStatus.CREATED).body(savedFarmer);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
        try {
            Farmer loggedInFarmer = farmerService.login(username, password);

            // Return the farmer's profile upon successful login
            return ResponseEntity.ok(loggedInFarmer);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
