package com.indiatour.etour.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.indiatour.etour.entity.TourPackage;
import com.indiatour.etour.service.TourPackageService;

@RestController
@RequestMapping("/packages")
public class TourPackageController {

    @Autowired
    private TourPackageService tourPackageService;

    @PostMapping
    public TourPackage savePackage(@RequestBody TourPackage tourPackage) {
        return tourPackageService.savePackage(tourPackage);
    }

    @GetMapping
    public List<TourPackage> getAllPackages() {
        return tourPackageService.getAllPackages();
    }

    @GetMapping("/{id}")
    public TourPackage getPackageById(@PathVariable Long id) {
        return tourPackageService.getPackageById(id);
    }

    @PutMapping("/{id}")
    public TourPackage updatePackage(@PathVariable Long id,
                                     @RequestBody TourPackage tourPackage) {
        return tourPackageService.updatePackage(id, tourPackage);
    }

    @DeleteMapping("/{id}")
    public String deletePackage(@PathVariable Long id) {
        tourPackageService.deletePackage(id);
        return "Tour Package deleted successfully!";
    }
}