package com.myfirstproject.springapplication.controllers;

import com.myfirstproject.springapplication.entity.Farmer;
import com.myfirstproject.springapplication.serviveLayer.FarmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/farmers")
public class FarmerController {

    @Autowired
    private FarmerService farmerService;

    @GetMapping
    public String listFarmers(Model model) {
        model.addAttribute("farmers", farmerService.getAllFarmers());
        return "farmer/list";  // Thymeleaf template path
    }

    @GetMapping("/new")
    public String showNewFarmerForm(Model model) {
        model.addAttribute("farmer", new Farmer());
        return "farmer/new";  // Thymeleaf template path
    }

    @PostMapping
    public String saveFarmer(@ModelAttribute Farmer farmer) {
        farmerService.saveFarmer(farmer);
        return "redirect:/farmers";
    }

    @GetMapping("/{id}/edit")
    public String showEditFarmerForm(@PathVariable Long id, Model model) {
        model.addAttribute("farmer", farmerService.getFarmerById(id).orElse(null));
        return "farmer/edit";  // Thymeleaf template path
    }

    @PostMapping("/{id}")
    public String updateFarmer(@PathVariable Long id, @ModelAttribute Farmer farmer) {
        farmer.setId(id);
        farmerService.saveFarmer(farmer);
        return "redirect:/farmers";
    }

    @GetMapping("/{id}/delete")
    public String deleteFarmer(@PathVariable Long id) {
        farmerService.deleteFarmer(id);
        return "redirect:/farmers";
    }
}

