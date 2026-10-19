package com.indiatour.etour.service;

import java.time.LocalDate;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.razorpay.RazorpayClient;
import com.razorpay.Utils;

import com.indiatour.etour.dto.request.RazorpayOrderRequest;
import com.indiatour.etour.dto.request.RazorpayVerifyRequest;
import com.indiatour.etour.dto.response.RazorpayOrderResponse;
import com.indiatour.etour.entity.Booking;
import com.indiatour.etour.entity.Payment;
import com.indiatour.etour.exception.BadRequestException;
import com.indiatour.etour.exception.ResourceNotFoundException;
import com.indiatour.etour.repository.BookingRepository;
import com.indiatour.etour.repository.PaymentRepository;

@Service
public class RazorpayServiceImpl implements RazorpayService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Value("${razorpay.key.id}")
    private String keyId;

    @Value("${razorpay.key.secret}")
    private String keySecret;

    @Override
    public RazorpayOrderResponse createOrder(RazorpayOrderRequest request) throws Exception {

        Booking booking = bookingRepository.findById(request.getBookingId())
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

        // Razorpay wants the amount in paise (smallest currency unit)
        int amountInPaise = (int) Math.round(booking.getTotalAmount() * 100);

        RazorpayClient razorpayClient = new RazorpayClient(keyId, keySecret);

        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", amountInPaise);
        orderRequest.put("currency", "INR");
        orderRequest.put("receipt", "booking_" + booking.getBookingId());

        com.razorpay.Order order = razorpayClient.orders.create(orderRequest);

        return new RazorpayOrderResponse(
                order.get("id"),
                keyId,
                booking.getTotalAmount(),
                "INR"
        );
    }

    @Override
    public Payment verifyAndConfirmPayment(RazorpayVerifyRequest request) throws Exception {

        JSONObject options = new JSONObject();
        options.put("razorpay_order_id", request.getRazorpayOrderId());
        options.put("razorpay_payment_id", request.getRazorpayPaymentId());
        options.put("razorpay_signature", request.getRazorpaySignature());

        boolean isValid = Utils.verifyPaymentSignature(options, keySecret);

        if (!isValid) {
            throw new BadRequestException("Payment signature verification failed");
        }

        Booking booking = bookingRepository.findById(request.getBookingId())
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

        // 1. Save the Payment record — same idea as your old Payment.js flow
        Payment payment = new Payment();
        payment.setAmount(booking.getTotalAmount());
        payment.setPaymentMethod("Razorpay");
        payment.setPaymentStatus("SUCCESS");
        payment.setPaymentDate(LocalDate.now());
        payment.setBooking(booking);
        payment.setRazorpayOrderId(request.getRazorpayOrderId());
        payment.setRazorpayPaymentId(request.getRazorpayPaymentId());

        Payment savedPayment = paymentRepository.save(payment);

        // 2. Confirm the booking — same as before
        booking.setBookingStatus("CONFIRMED");
        bookingRepository.save(booking);

        return savedPayment;
    }
}