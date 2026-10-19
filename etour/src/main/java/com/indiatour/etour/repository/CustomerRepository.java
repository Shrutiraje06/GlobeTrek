package com.indiatour.etour.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.indiatour.etour.entity.Customer;

public interface CustomerRepository
        extends JpaRepository<Customer, Long> {

}