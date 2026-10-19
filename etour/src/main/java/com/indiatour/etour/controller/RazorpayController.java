package com.indiatour.etour.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.indiatour.etour.dto.request.RazorpayOrderRequest;
import com.indiatour.etour.dto.request.RazorpayVerifyRequest;
import com.indiatour.etour.dto.response.RazorpayOrderResponse;
import com.indiatour.etour.entity.Payment;
import com.indiatour.etour.service.RazorpayService;

@RestController
@RequestMapping("/payments/razorpay")
@CrossOrigin(origins = "*")
public class RazorpayController {

    @Autowired
    private RazorpayService razorpayService;

    @PostMapping("/create-order")
    public RazorpayOrderResponse createOrder(@RequestBody RazorpayOrderRequest request) throws Exception {
        return razorpayService.createOrder(request);
    }

    @PostMapping("/verify")
    public Payment verifyPayment(@RequestBody RazorpayVerifyRequest request) throws Exception {
        return razorpayService.verifyAndConfirmPayment(request);
    }
}