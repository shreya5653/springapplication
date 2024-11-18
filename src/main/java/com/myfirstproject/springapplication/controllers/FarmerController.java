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
    public ResponseEntity<String> register(@RequestBody Farmer farmer) {
        String response = farmerService.register(farmer);

        switch(response) {
            case "User registered successfully":
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            case "Email already registered":
                return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
            case "Invalid email format":
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        String response = farmerService.login(username, password);

        if (response.equals("Login successful")) {
            return ResponseEntity.ok(response);  // You can send back a token if needed, for now it's a simple message.
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
    @GetMapping("/profile")
    public ResponseEntity<?> getFarmerProfile(@RequestParam String username) {
        if (username == null || username.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Username is required");
        }

        Farmer farmer = farmerService.getFarmerByUsername(username);

        if (farmer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Farmer not found");
        }
        return ResponseEntity.ok(farmer);
    }

}
