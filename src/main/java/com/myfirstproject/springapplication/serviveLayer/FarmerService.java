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

    public String register(Farmer farmer) {
        if (!isValidEmail(farmer.getEmail())) {
            return "Invalid email format";
        }

        Optional<Farmer> existingFarmer = farmerRepository.findByEmail(farmer.getEmail());
        if (existingFarmer.isPresent()) {
            return "Email already registered";
        }
        farmerRepository.save(farmer);
        return "User registered successfully";
    }

    public String login(String username, String password) {
        Optional<Farmer> farmer = farmerRepository.findByUsernameAndPassword(username, password);
        return farmer.isPresent() ? "Login successful" : "Invalid credentials";
    }

    private boolean isValidEmail(String email) {
        return Pattern.matches(EMAIL_REGEX, email);
    }
}
