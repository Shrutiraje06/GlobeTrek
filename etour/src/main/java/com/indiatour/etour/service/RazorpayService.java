package com.indiatour.etour.service;

import com.indiatour.etour.dto.request.RazorpayOrderRequest;
import com.indiatour.etour.dto.request.RazorpayVerifyRequest;
import com.indiatour.etour.dto.response.RazorpayOrderResponse;
import com.indiatour.etour.entity.Payment;

public interface RazorpayService {

    RazorpayOrderResponse createOrder(RazorpayOrderRequest request) throws Exception;

    Payment verifyAndConfirmPayment(RazorpayVerifyRequest request) throws Exception;
}