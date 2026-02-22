package com.railconnect.bookingservice.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "bookings")
public class Booking {

    @Id
    private String id;

    private String pnr;
    private String userEmail;

    // Train details
    private String trainName;
    private String fromStation;
    private String toStation;
    private String journeyDate;
    private String depTime;
    private String arrTime;
    private String travelClass;

    // Contact details
    private String contactMobile;
    private String contactEmail;

    // Amount
    private double totalAmount;

    // Status
    private String status; // PAYMENT_PENDING / BOOKED / CANCELLED / CHART_PREPARED
    private LocalDateTime bookingTimestamp;

    private List<Passenger> passengers;

    public Booking() {}

    // --- getters/setters ---
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getPnr() { return pnr; }
    public void setPnr(String pnr) { this.pnr = pnr; }

    public String getUserEmail() { return userEmail; }
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }

    public String getTrainName() { return trainName; }
    public void setTrainName(String trainName) { this.trainName = trainName; }

    public String getFromStation() { return fromStation; }
    public void setFromStation(String fromStation) { this.fromStation = fromStation; }

    public String getToStation() { return toStation; }
    public void setToStation(String toStation) { this.toStation = toStation; }

    public String getJourneyDate() { return journeyDate; }
    public void setJourneyDate(String journeyDate) { this.journeyDate = journeyDate; }

    public String getDepTime() { return depTime; }
    public void setDepTime(String depTime) { this.depTime = depTime; }

    public String getArrTime() { return arrTime; }
    public void setArrTime(String arrTime) { this.arrTime = arrTime; }

    public String getTravelClass() { return travelClass; }
    public void setTravelClass(String travelClass) { this.travelClass = travelClass; }

    public String getContactMobile() { return contactMobile; }
    public void setContactMobile(String contactMobile) { this.contactMobile = contactMobile; }

    public String getContactEmail() { return contactEmail; }
    public void setContactEmail(String contactEmail) { this.contactEmail = contactEmail; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getBookingTimestamp() { return bookingTimestamp; }
    public void setBookingTimestamp(LocalDateTime bookingTimestamp) { this.bookingTimestamp = bookingTimestamp; }

    public List<Passenger> getPassengers() { return passengers; }
    public void setPassengers(List<Passenger> passengers) { this.passengers = passengers; }
}