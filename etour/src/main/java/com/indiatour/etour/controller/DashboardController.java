package com.indiatour.etour.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.indiatour.etour.dto.response.DashboardResponse;
import com.indiatour.etour.service.DashboardService;

@RestController
@RequestMapping("/admin/dashboard")
@CrossOrigin(origins = "*")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping
    public DashboardResponse getDashboard() {

        return dashboardService.getDashboard();

    }
}