package com.myfirstproject.springapplication.controllers;

import com.myfirstproject.springapplication.entity.DailyWageWorker;
import com.myfirstproject.springapplication.serviveLayer.DailyWageWorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/workers")
public class DailyWageWorkerController {

    @Autowired
    private DailyWageWorkerService dailyWageWorkerService;

    @GetMapping
    public String listWorkers(@RequestParam(value = "farmerId", required = false) Long farmerId, Model model) {
        model.addAttribute("workers", dailyWageWorkerService.getAllWorkers());
        model.addAttribute("farmerId", farmerId); // Pass farmerId for use in the view
        return "farmer/worker/list";  // Updated Thymeleaf template path
    }

    @GetMapping("/new")
    public String showNewWorkerForm(Model model) {
        model.addAttribute("worker", new DailyWageWorker());
        return "farmer/worker/new";  // Updated Thymeleaf template path
    }

    @PostMapping
    public String saveWorker(@ModelAttribute DailyWageWorker worker, @RequestParam("farmerId") Long farmerId) {
        dailyWageWorkerService.saveWorker(worker);
        return "redirect:/workers?farmerId=" + farmerId; // Redirect to the worker list with the associated farmerId
    }

    @GetMapping("/{id}/edit")
    public String showEditWorkerForm(@PathVariable Long id, Model model) {
        DailyWageWorker worker = dailyWageWorkerService.getWorkerById(id).orElse(null);
        if (worker == null) {
            return "redirect:/workers"; // Redirect if worker not found
        }
        model.addAttribute("worker", worker);
        return "farmer/worker/edit";  // Updated Thymeleaf template path
    }

    @PostMapping("/{id}")
    public String updateWorker(@PathVariable Long id, @ModelAttribute DailyWageWorker worker) {
        worker.setId(id);
        dailyWageWorkerService.saveWorker(worker);
        return "redirect:/workers";
    }

    @GetMapping("/{id}/delete")
    public String deleteWorker(@PathVariable Long id) {
        dailyWageWorkerService.deleteWorker(id);
        return "redirect:/workers";
    }

    @GetMapping("/{id}/wages")
    public String viewWorkerWages(@PathVariable Long id, Model model) {
        model.addAttribute("workerId", id); // Pass the worker ID to be used in the wage list view
        return "redirect:/wages?workerId=" + id; // Redirect to the wage list for this worker
    }
}



//
//
//package com.myfirstproject.springapplication.controllers;
//
//import com.myfirstproject.springapplication.entity.DailyWageWorker;
//import com.myfirstproject.springapplication.serviveLayer.DailyWageWorkerService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//@RequestMapping("/workers")
//public class DailyWageWorkerController {
//
//    @Autowired
//    private DailyWageWorkerService dailyWageWorkerService;
//
//    @GetMapping
//    public String listWorkers(Model model) {
//        model.addAttribute("workers", dailyWageWorkerService.getAllWorkers());
//        return "farmer/worker/list";  // Updated Thymeleaf template path
//    }
//
//    @GetMapping("/new")
//    public String showNewWorkerForm(Model model) {
//        model.addAttribute("worker", new DailyWageWorker());
//        return "farmer/worker/new";  // Updated Thymeleaf template path
//    }
//
//    @PostMapping
//    public String saveWorker(@ModelAttribute DailyWageWorker worker) {
//        dailyWageWorkerService.saveWorker(worker);
//        return "redirect:/workers";
//    }
//
//    @GetMapping("/{id}/edit")
//    public String showEditWorkerForm(@PathVariable Long id, Model model) {
//        DailyWageWorker worker = dailyWageWorkerService.getWorkerById(id).orElse(null);
//        if (worker == null) {
//            return "redirect:/workers"; // Redirect if worker not found
//        }
//        model.addAttribute("worker", worker);
//        return "farmer/worker/edit";  // Updated Thymeleaf template path
//    }
//
//    @PostMapping("/{id}")
//    public String updateWorker(@PathVariable Long id, @ModelAttribute DailyWageWorker worker) {
//        worker.setId(id);
//        dailyWageWorkerService.saveWorker(worker);
//        return "redirect:/workers";
//    }
//
//    @GetMapping("/{id}/delete")
//    public String deleteWorker(@PathVariable Long id) {
//        dailyWageWorkerService.deleteWorker(id);
//        return "redirect:/workers";
//    }
//}
//
//
//
