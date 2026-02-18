package com.railconnect.paymentservice.repository;

import com.railconnect.paymentservice.entity.PaymentDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface PaymentRepository extends MongoRepository<PaymentDetails, String> {
    
    // ✅ This matches the 'pnr' field in your entity
    List<PaymentDetails> findByPnr(String pnr);
    
    // ✅ Fix: Changed from findByRazorpayOrderId to findByOrderId 
    // to match the field name in your PaymentDetails entity
    PaymentDetails findByOrderId(String orderId);
}