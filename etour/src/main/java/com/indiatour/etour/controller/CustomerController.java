package com.indiatour.etour.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.indiatour.etour.entity.Customer;
import com.indiatour.etour.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customers")
@CrossOrigin(origins = "*")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // ==========================
    // Save Customer
    // ==========================
    @PostMapping
    public Customer saveCustomer(@Valid @RequestBody Customer customer) {

        return customerService.saveCustomer(customer);
    }

    // ==========================
    // Get All Customers
    // ==========================
    @GetMapping
    public List<Customer> getAllCustomers() {

        return customerService.getAllCustomers();
    }

    // ==========================
    // Get Customer By ID
    // ==========================
    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {

        return customerService.getCustomerById(id);
    }

    // ==========================
    // Update Customer
    // ==========================
    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id,
                                   @Valid @RequestBody Customer customer) {

        return customerService.updateCustomer(id, customer);
    }

    // ==========================
    // Delete Customer
    // ==========================
    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable Long id) {

        customerService.deleteCustomer(id);

        return "Customer deleted successfully!";
    }
}