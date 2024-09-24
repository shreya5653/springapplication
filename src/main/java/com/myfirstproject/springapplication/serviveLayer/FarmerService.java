package com.myfirstproject.springapplication.serviveLayer;

import com.myfirstproject.springapplication.entity.Farmer;
import com.myfirstproject.springapplication.repositories.FarmerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FarmerService {

    @Autowired
    private FarmerRepository farmerRepository;

    // Get all farmers
    public List<Farmer> getAllFarmers() {
        return farmerRepository.findAll();
    }

    // Get farmer by ID
    public Optional<Farmer> getFarmerById(Long id) {
        return farmerRepository.findById(id);
    }

    // Save or update farmer
    public Farmer saveFarmer(Farmer farmer) {
        return farmerRepository.save(farmer);
    }

    // Delete farmer by ID
    public void deleteFarmer(Long id) {
        farmerRepository.deleteById(id);
    }
}
