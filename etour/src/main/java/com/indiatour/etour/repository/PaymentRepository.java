package com.indiatour.etour.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.indiatour.etour.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}