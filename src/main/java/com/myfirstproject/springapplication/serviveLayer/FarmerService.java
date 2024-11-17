package com.myfirstproject.springapplication.serviveLayer;

import com.myfirstproject.springapplication.entity.Farmer;
import com.myfirstproject.springapplication.repositories.FarmerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class FarmerService {

    @Autowired
    private FarmerRepository farmerRepository;

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    public Farmer register(Farmer farmer) {
        if (!isValidEmail(farmer.getEmail())) {
            throw new IllegalArgumentException("Invalid email format");
        }

        Optional<Farmer> existingFarmer = farmerRepository.findByEmail(farmer.getEmail());
        if (existingFarmer.isPresent()) {
            throw new IllegalStateException("Email already registered");
        }

        // Save and return the saved farmer entity
        return farmerRepository.save(farmer);
    }

    public Farmer login(String username, String password) {
        Optional<Farmer> farmer = farmerRepository.findByUsernameAndPassword(username, password);
        return farmer.orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));
    }

    private boolean isValidEmail(String email) {
        return Pattern.matches(EMAIL_REGEX, email);
    }
}
