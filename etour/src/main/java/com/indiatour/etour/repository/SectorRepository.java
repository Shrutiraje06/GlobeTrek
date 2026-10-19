package com.indiatour.etour.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.indiatour.etour.entity.Sector;

@Repository
public interface SectorRepository extends JpaRepository<Sector, Long> {

}