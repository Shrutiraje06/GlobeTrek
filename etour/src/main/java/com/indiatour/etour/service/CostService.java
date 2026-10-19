package com.indiatour.etour.service;

import java.util.List;

import com.indiatour.etour.entity.Cost;

public interface CostService {

    Cost saveCost(Cost cost);

    List<Cost> getAllCosts();

    Cost getCostById(Long id);

    Cost updateCost(Long id, Cost cost);

    void deleteCost(Long id);
}