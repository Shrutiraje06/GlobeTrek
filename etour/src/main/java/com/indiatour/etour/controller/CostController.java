package com.indiatour.etour.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.indiatour.etour.entity.Cost;
import com.indiatour.etour.service.CostService;

@RestController
@RequestMapping("/costs")
@CrossOrigin(origins = "*")
public class CostController {

    @Autowired
    private CostService costService;

    // Save Cost
    @PostMapping
    public Cost saveCost(@RequestBody Cost cost) {
        return costService.saveCost(cost);
    }

    // Get All Costs
    @GetMapping
    public List<Cost> getAllCosts() {
        return costService.getAllCosts();
    }

    // Get Cost By ID
    @GetMapping("/{id}")
    public Cost getCostById(@PathVariable Long id) {
        return costService.getCostById(id);
    }

    // Update Cost
    @PutMapping("/{id}")
    public Cost updateCost(@PathVariable Long id,
                           @RequestBody Cost cost) {

        return costService.updateCost(id, cost);
    }

    // Delete Cost
    @DeleteMapping("/{id}")
    public String deleteCost(@PathVariable Long id) {

        costService.deleteCost(id);

        return "Cost deleted successfully.";
    }
}