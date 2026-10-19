package com.indiatour.etour.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indiatour.etour.entity.TourPackage;
import com.indiatour.etour.repository.TourPackageRepository;

@Service
public class TourPackageServiceImpl implements TourPackageService {

    @Autowired
    private TourPackageRepository tourPackageRepository;

    @Override
    public TourPackage savePackage(TourPackage tourPackage) {
        return tourPackageRepository.save(tourPackage);
    }

    @Override
    public List<TourPackage> getAllPackages() {
        return tourPackageRepository.findAll();
    }

    @Override
    public TourPackage getPackageById(Long id) {
        return tourPackageRepository.findById(id).orElse(null);
    }

    @Override
    public TourPackage updatePackage(Long id, TourPackage tourPackage) {

        TourPackage existingPackage = tourPackageRepository.findById(id).orElse(null);

        if (existingPackage != null) {

            existingPackage.setPackageName(tourPackage.getPackageName());
            existingPackage.setDestination(tourPackage.getDestination());
            existingPackage.setDuration(tourPackage.getDuration());
            existingPackage.setPrice(tourPackage.getPrice());
            existingPackage.setDescription(tourPackage.getDescription());
            existingPackage.setImageUrl(tourPackage.getImageUrl());

            return tourPackageRepository.save(existingPackage);
        }

        return null;
    }

    @Override
    public void deletePackage(Long id) {
        tourPackageRepository.deleteById(id);
    }
}