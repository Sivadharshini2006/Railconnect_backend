package com.railconnect.trainservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "fares")
public class Fare {
    @Id
    private String id;
    private String className;
    private double baseRate;
    private double perKm;
    private double tatkal;
    private double reservation;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public double getBaseRate() {
		return baseRate;
	}
	public void setBaseRate(double baseRate) {
		this.baseRate = baseRate;
	}
	public double getPerKm() {
		return perKm;
	}
	public void setPerKm(double perKm) {
		this.perKm = perKm;
	}
	public double getTatkal() {
		return tatkal;
	}
	public void setTatkal(double tatkal) {
		this.tatkal = tatkal;
	}
	public double getReservation() {
		return reservation;
	}
	public void setReservation(double reservation) {
		this.reservation = reservation;
	}
    
    
}