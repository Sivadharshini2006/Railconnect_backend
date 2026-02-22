package com.railconnect.trainservice.model;

public class TrainClass {
    private String type;
    private double price;
    private String status;
    private String label;

    public TrainClass() {} // ✅ IMPORTANT for Mongo mapping

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }
}