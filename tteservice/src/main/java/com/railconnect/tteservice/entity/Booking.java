package com.railconnect.tteservice.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "bookings")
public class Booking {

    @Id
    private String id;

    private String pnr;
    private String status; // ADD THIS to capture "BOOKED"
    private String bookingTimestamp; // ADD THIS
    private String trainId;
    private String trainName;
    private String date;

    private List<Passenger> passengers;
    
 // GENERATE GETTERS AND SETTERS FOR status AND bookingTimestamp
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getBookingTimestamp() { return bookingTimestamp; }
    public void setBookingTimestamp(String bookingTimestamp) { this.bookingTimestamp = bookingTimestamp; }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPnr() {
		return pnr;
	}

	public void setPnr(String pnr) {
		this.pnr = pnr;
	}

	public String getTrainId() {
		return trainId;
	}

	public void setTrainId(String trainId) {
		this.trainId = trainId;
	}

	public String getTrainName() {
		return trainName;
	}

	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}

}
