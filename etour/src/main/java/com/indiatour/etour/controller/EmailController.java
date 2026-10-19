package com.indiatour.etour.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.indiatour.etour.service.EmailService;

@RestController
@RequestMapping("/email")
@CrossOrigin(origins = "*")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public String sendEmail() {

        emailService.sendEmail(
                "receiver@gmail.com",   // Replace with recipient email
                "Booking Confirmation",
                "Dear Rahul,\n\n"
                + "Your IndiaTour booking has been confirmed successfully.\n\n"
                + "Thank you for choosing IndiaTour.\n\n"
                + "Have a wonderful journey!");

        return "Email Sent Successfully";
    }
}