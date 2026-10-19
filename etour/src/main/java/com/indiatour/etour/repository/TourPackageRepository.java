package com.indiatour.etour.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.indiatour.etour.entity.TourPackage;

public interface TourPackageRepository
        extends JpaRepository<TourPackage, Long> {

}