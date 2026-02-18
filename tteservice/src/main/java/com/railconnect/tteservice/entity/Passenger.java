package com.railconnect.tteservice.entity;

import org.springframework.data.mongodb.core.mapping.Field;

public class Passenger {

    private String name;
    private int age;
    private String gender;
    private String status; 
    private String berthPreference;
    private String coach;
    private int seatNumber;
    private String assignedBerth; // From your screenshot

    // REQUIRED: Empty constructor for MongoDB mapping
    public Passenger() {}

    // GETTERS AND SETTERS (Must exist for all fields)
    public String getAssignedBerth() { return assignedBerth; }
    public void setAssignedBerth(String assignedBerth) { this.assignedBerth = assignedBerth; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getBerthPreference() { return berthPreference; }
    public void setBerthPreference(String berthPreference) { this.berthPreference = berthPreference; }

    public String getCoach() { return coach; }
    public void setCoach(String coach) { this.coach = coach; }

    public int getSeatNumber() { return seatNumber; }
    public void setSeatNumber(int seatNumber) { this.seatNumber = seatNumber; }
}