package com.railconnect.trainservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;
@Document(collection = "trains")
public class Train {
    @Id
    private String id;

    private String trainNumber;
    private String trainName;
    private String source;
    private String destination;
    private int totalSeats;
    private String depTime;
    private String arrTime;
    private String duration;

    private String rating;
    private boolean isAC;
    private boolean hasTatkal;

    private List<TrainClass> classes;
    private List<String> runningDays;
    private String variantId;
    private double ticketPrice;
   
    public Train() {}

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

	public String getDepTime() {
		return depTime;
	}

	public void setDepTime(String depTime) {
		this.depTime = depTime;
	}

	public String getArrTime() {
		return arrTime;
	}

	public void setArrTime(String arrTime) {
		this.arrTime = arrTime;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public boolean isAC() {
		return isAC;
	}

	public void setAC(boolean isAC) {
		this.isAC = isAC;
	}

	public boolean isHasTatkal() {
		return hasTatkal;
	}

	public void setHasTatkal(boolean hasTatkal) {
		this.hasTatkal = hasTatkal;
	}

	public List<TrainClass> getClasses() {
		return classes;
	}

	public void setClasses(List<TrainClass> classes) {
		this.classes = classes;
	}
	public List<String> getRunningDays() {
	    return runningDays;
	}

	public void setRunningDays(List<String> runningDays) {
	    this.runningDays = runningDays;
	}
	public int getTotalSeats() {
	    return totalSeats;
	}

	public void setTotalSeats(int totalSeats) {
	    this.totalSeats = totalSeats;
	}
	 public String getVariantId()
	    { return variantId; }
	    public void setVariantId(String variantId) 
	    { this.variantId = variantId; }

		public double getTicketPrice() {
			return ticketPrice;
		}

		public void setTicketPrice(double ticketPrice) {
			this.ticketPrice = ticketPrice;
		}
    

    
}