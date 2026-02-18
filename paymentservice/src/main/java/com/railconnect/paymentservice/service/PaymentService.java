package com.railconnect.paymentservice.service;

import com.razorpay.Utils;
import com.razorpay.Order;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.railconnect.paymentservice.entity.PaymentDetails;
import com.railconnect.paymentservice.repository.PaymentRepository;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Service
public class PaymentService {

    @Value("${razorpay.api.key}")
    private String apiKey;

    @Value("${razorpay.api.secret}")
    private String apiSecret;
    
    @Autowired
    private PaymentRepository paymentRepository;

    public String createOrder(double amount, String pnr) throws RazorpayException {
        RazorpayClient client = new RazorpayClient(apiKey, apiSecret);

        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", amount * 100); // Razorpay expects paise
        orderRequest.put("currency", "INR");
        orderRequest.put("receipt", "receipt_" + pnr);

        Order order = client.orders.create(orderRequest);
        return order.toString(); 
    }
 // 1. Add pnr to the method parameters
    public boolean verifyAndSavePayment(String orderId, String paymentId, String signature, String pnr) {
        // ✅ ADD THIS LINE: It will print the signature to your Eclipse/IntelliJ console
        System.out.println("--- RECEIVED DATA FROM FRONTEND ---");
        System.out.println("Order ID: " + orderId);
        System.out.println("Payment ID: " + paymentId);
        System.out.println("Signature: " + signature);
        System.out.println("PNR: " + pnr);
        System.out.println("------------------------------------");

        try {
            JSONObject options = new JSONObject();
            options.put("razorpay_order_id", orderId);
            options.put("razorpay_payment_id", paymentId);
            options.put("razorpay_signature", signature);

            boolean isOk = Utils.verifyPaymentSignature(options, apiSecret);

             
            
            if (isOk) {
                // 2. Pass pnr here so it is stored in MongoDB
                PaymentDetails details = new PaymentDetails(orderId, paymentId, "SUCCESS", pnr);
                paymentRepository.save(details); 
            }
            return isOk;
        } catch (Exception e) {
            return false;
        }
    }
    

    public List<PaymentDetails> getPaymentHistory(String pnr) {
        return paymentRepository.findByPnr(pnr);
    }
}