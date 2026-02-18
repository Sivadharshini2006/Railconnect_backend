package com.railconnect.paymentservice.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "payments")
public class PaymentDetails {
    @Id
    private String id;
    private String orderId;
    private String paymentId;
    private String status;
    private String pnr; // Added this field
    
    public PaymentDetails() {}

    // Updated constructor to include PNR
    public PaymentDetails(String orderId, String paymentId, String status, String pnr) {
        this.orderId = orderId;
        this.paymentId = paymentId;
        this.status = status;
        this.pnr = pnr;
    }
    
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
		
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPnr() {
		return pnr;
	}

	public void setPnr(String pnr) {
		this.pnr = pnr;
	}
	
    
}