package com.railconnect.trainservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Data
@Document(collection = "trains")
public class Train {
    @Id
    private String id;
    private String trainNumber; 
    private String trainName;   
    private String source;
    private String destination;
    private int totalSeats;
    private double ticketPrice;
    
    
    
    
    public String getId() {
		return id;
	}




	public void setId(String id) {
		this.id = id;
	}




	public String getTrainNumber() {
		return trainNumber;
	}




	public void setTrainNumber(String trainNumber) {
		this.trainNumber = trainNumber;
	}




	public String getTrainName() {
		return trainName;
	}




	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}




	public String getSource() {
		return source;
	}




	public void setSource(String source) {
		this.source = source;
	}




	public String getDestination() {
		return destination;
	}




	public void setDestination(String destination) {
		this.destination = destination;
	}




	public int getTotalSeats() {
		return totalSeats;
	}




	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}




	public double getTicketPrice() {
		return ticketPrice;
	}




	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}




	public List<String> getRunningDays() {
		return runningDays;
	}




	public void setRunningDays(List<String> runningDays) {
		this.runningDays = runningDays;
	}




	private List<String> runningDays;
}