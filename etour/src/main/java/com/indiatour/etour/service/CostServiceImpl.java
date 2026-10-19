package com.indiatour.etour.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indiatour.etour.entity.Cost;
import com.indiatour.etour.repository.CostRepository;

@Service
public class CostServiceImpl implements CostService {

    @Autowired
    private CostRepository costRepository;

    @Override
    public Cost saveCost(Cost cost) {
        return costRepository.save(cost);
    }

    @Override
    public List<Cost> getAllCosts() {
        return costRepository.findAll();
    }

    @Override
    public Cost getCostById(Long id) {
        return costRepository.findById(id).orElse(null);
    }

    @Override
    public Cost updateCost(Long id, Cost cost) {

        Cost existingCost = costRepository.findById(id).orElse(null);

        if (existingCost != null) {

            existingCost.setHotelCost(cost.getHotelCost());
            existingCost.setTransportCost(cost.getTransportCost());
            existingCost.setFoodCost(cost.getFoodCost());
            existingCost.setGuideCost(cost.getGuideCost());
            existingCost.setGst(cost.getGst());
            existingCost.setTotalCost(cost.getTotalCost());
            existingCost.setTourPackage(cost.getTourPackage());

            return costRepository.save(existingCost);
        }

        return null;
    }

    @Override
    public void deleteCost(Long id) {
        costRepository.deleteById(id);
    }
}