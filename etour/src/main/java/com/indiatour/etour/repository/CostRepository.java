package com.indiatour.etour.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.indiatour.etour.entity.Cost;

@Repository
public interface CostRepository extends JpaRepository<Cost, Long> {

}