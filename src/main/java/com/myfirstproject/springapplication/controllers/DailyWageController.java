package com.myfirstproject.springapplication.controllers;

import com.myfirstproject.springapplication.entity.DailyWage;
import com.myfirstproject.springapplication.serviveLayer.DailyWageService;
import com.myfirstproject.springapplication.serviveLayer.DailyWageWorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/wages")
public class DailyWageController {

    @Autowired
    private DailyWageService dailyWageService;

    @Autowired
    private DailyWageWorkerService dailyWageWorkerService;

    @GetMapping
    public String listWages(@RequestParam Long workerId, Model model) {
        // Get all wages for the specified worker
        List<DailyWage> workerWages = dailyWageService.getWagesByWorkerId(workerId);
        model.addAttribute("wages", workerWages);
        model.addAttribute("workerId", workerId); // Pass the worker ID for reference in the view
        model.addAttribute("workerName", dailyWageWorkerService.getWorkerById(workerId).map(w -> w.getName()).orElse("Worker")); // Display worker's name if available
        return "farmer/wage/list";  // Updated Thymeleaf template path
    }

    @GetMapping("/new")
    public String showNewWageForm(@RequestParam Long workerId, Model model) {
        DailyWage wage = new DailyWage();
        wage.setWorker(dailyWageWorkerService.getWorkerById(workerId).orElse(null)); // Associate wage with the selected worker

        model.addAttribute("wage", wage);
        model.addAttribute("workers", dailyWageWorkerService.getAllWorkers());
        return "farmer/wage/new";  // Updated Thymeleaf template path
    }

    @PostMapping
    public String saveWage(@ModelAttribute DailyWage wage) {
        dailyWageService.saveWage(wage);
        return "redirect:/wages?workerId=" + wage.getWorker().getId(); // Redirect to the worker's wage list
    }

    @GetMapping("/edit")
    public String showEditWageForm(@RequestParam Long workerId, @RequestParam LocalDate date, Model model) {
        List<DailyWage> wages = dailyWageService.getWagesByWorkerId(workerId);

        // Find the wage entry for the specific date
        DailyWage wageToEdit = wages.stream()
                .filter(wage -> wage.getDate().equals(date))
                .findFirst()
                .orElse(null);

        if (wageToEdit == null) {
            // Handle case where wage entry doesn't exist
            model.addAttribute("error", "No wage found for the specified worker and date.");
            return "farmer/wage/error"; // Updated error page path
        }

        model.addAttribute("wage", wageToEdit);
        model.addAttribute("workers", dailyWageWorkerService.getAllWorkers());
        return "farmer/wage/edit";  // Updated Thymeleaf template path
    }

    @PostMapping("/{id}")
    public String updateWage(@PathVariable Long id, @ModelAttribute DailyWage wage) {
        wage.setId(id);
        dailyWageService.saveWage(wage);
        return "redirect:/wages?workerId=" + wage.getWorker().getId(); // Redirect to the worker's wage list
    }

    @GetMapping("/{id}/delete")
    public String deleteWage(@PathVariable Long id, @RequestParam Long workerId) {
        dailyWageService.deleteWageById(id);
        return "redirect:/wages?workerId=" + workerId; // Redirect to the worker's wage list
    }
}


//package com.myfirstproject.springapplication.controllers;
//
//import com.myfirstproject.springapplication.entity.DailyWage;
//import com.myfirstproject.springapplication.serviveLayer.DailyWageService;
//import com.myfirstproject.springapplication.serviveLayer.DailyWageWorkerService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDate;
//import java.util.List;
//
//@Controller
//@RequestMapping("/wages")
//public class DailyWageController {
//
//    @Autowired
//    private DailyWageService dailyWageService;
//
//    @Autowired
//    private DailyWageWorkerService dailyWageWorkerService;
//
//    @GetMapping
//    public String listWages(@RequestParam Long workerId, Model model) {
//        // Get all wages for the specified worker
//        List<DailyWage> workerWages = dailyWageService.getWagesByWorkerId(workerId);
//        model.addAttribute("wages", workerWages);
//        model.addAttribute("workerId", workerId); // Pass the worker ID for reference in the view
//        return "farmer/wage/list";  // Updated Thymeleaf template path
//    }
//
//    @GetMapping("/new")
//    public String showNewWageForm(Model model) {
//        model.addAttribute("wage", new DailyWage());
//        model.addAttribute("workers", dailyWageWorkerService.getAllWorkers());
//        return "farmer/wage/new";  // Updated Thymeleaf template path
//    }
//
//    @PostMapping
//    public String saveWage(@ModelAttribute DailyWage wage) {
//        dailyWageService.saveWage(wage);
//        return "redirect:/wages?workerId=" + wage.getWorker().getId(); // Redirect to the worker's wage list
//    }
//
//    @GetMapping("/edit")
//    public String showEditWageForm(@RequestParam Long workerId, @RequestParam LocalDate date, Model model) {
//        List<DailyWage> wages = dailyWageService.getWagesByWorkerId(workerId);
//
//        // Find the wage entry for the specific date
//        DailyWage wageToEdit = wages.stream()
//                .filter(wage -> wage.getDate().equals(date))
//                .findFirst()
//                .orElse(null);
//
//        if (wageToEdit == null) {
//            // Handle case where wage entry doesn't exist
//            model.addAttribute("error", "No wage found for the specified worker and date.");
//            return "farmer/wage/error"; // Updated error page path
//        }
//
//        model.addAttribute("wage", wageToEdit);
//        model.addAttribute("workers", dailyWageWorkerService.getAllWorkers());
//        return "farmer/wage/edit";  // Updated Thymeleaf template path
//    }
//
//    @PostMapping("/{id}")
//    public String updateWage(@PathVariable Long id, @ModelAttribute DailyWage wage) {
//        wage.setId(id);
//        dailyWageService.saveWage(wage);
//        return "redirect:/wages?workerId=" + wage.getWorker().getId(); // Redirect to the worker's wage list
//    }
//
//    @GetMapping("/{id}/delete")
//    public String deleteWage(@PathVariable Long id) {
//        DailyWage wage = dailyWageService.getWagesByWorkerId(id).get(0); // Fetch the wage to get the worker's ID
//        dailyWageService.deleteWageById(id);
//        return "redirect:/wages?workerId=" + wage.getWorker().getId(); // Redirect to the worker's wage list
//    }
//}
