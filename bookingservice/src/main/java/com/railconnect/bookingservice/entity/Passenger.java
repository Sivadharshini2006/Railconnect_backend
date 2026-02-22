package com.railconnect.bookingservice.entity;

public class Passenger {

    private String name;
    private int age;
    private String gender;
    private String berthPreference; // must match frontend key

    // assigned fields (optional)
    private String coach;
    private int seatNumber;
    private String assignedBerth;
    private String status;

    public Passenger() {}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getBerthPreference() { return berthPreference; }
    public void setBerthPreference(String berthPreference) { this.berthPreference = berthPreference; }

    public String getCoach() { return coach; }
    public void setCoach(String coach) { this.coach = coach; }

    public int getSeatNumber() { return seatNumber; }
    public void setSeatNumber(int seatNumber) { this.seatNumber = seatNumber; }

    public String getAssignedBerth() { return assignedBerth; }
    public void setAssignedBerth(String assignedBerth) { this.assignedBerth = assignedBerth; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}