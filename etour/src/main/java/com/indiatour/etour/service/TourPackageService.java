package com.indiatour.etour.service;

import java.util.List;

import com.indiatour.etour.entity.TourPackage;

public interface TourPackageService {

    TourPackage savePackage(TourPackage tourPackage);

    List<TourPackage> getAllPackages();

    TourPackage getPackageById(Long id);

    TourPackage updatePackage(Long id, TourPackage tourPackage);

    void deletePackage(Long id);
}