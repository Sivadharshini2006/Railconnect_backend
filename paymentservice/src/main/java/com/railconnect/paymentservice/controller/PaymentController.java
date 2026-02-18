package com.railconnect.paymentservice.controller;

import com.railconnect.paymentservice.dto.PaymentRequest;
import com.railconnect.paymentservice.entity.PaymentDetails;
import com.railconnect.paymentservice.service.PaymentService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin(origins = "http://localhost:5173") 
public class PaymentController {

	@Autowired
    private PaymentService paymentService;

    @PostMapping("/create-order")
    public String createOrder(@RequestBody PaymentRequest request) {
        try {
            // Pass both amount and pnr to the service
            return paymentService.createOrder(request.getAmount(), request.getPnr());
        } catch (Exception e) {
            return "Error creating order: " + e.getMessage();
        }
    }

    @PostMapping("/verify")
    public String verifyPayment(
            @RequestParam String orderId,
            @RequestParam String paymentId,
            @RequestParam String signature,
            @RequestParam String pnr) { // ✅ Added pnr as a parameter
        
        // Match the service method name exactly
        boolean isOk = paymentService.verifyAndSavePayment(orderId, paymentId, signature, pnr);
        
        return isOk ? "Payment Verified and Stored Successfully" : "Payment Verification Failed";
    }

    @GetMapping("/history/{pnr}")
    public List<PaymentDetails> getHistory(@PathVariable String pnr) {
        return paymentService.getPaymentHistory(pnr);
    }
}