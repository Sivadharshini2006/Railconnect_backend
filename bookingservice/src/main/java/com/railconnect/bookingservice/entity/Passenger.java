package com.railconnect.bookingservice.entity;

public class Passenger {
    private String name;
    private int age;
    private String gender;
    private String status; 
    private String berthPreference;
    private String coach;
    private int seatNumber;
    private String assignedBerth;

    public Passenger() {}

    // --- ADD THIS METHOD TO FIX THE ERROR ---
    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    // --- ENSURE THESE ALSO EXIST ---
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

	// Fixes "The method setCoach is undefined"
    public void setCoach(String coach) { this.coach = coach; }
    public String getCoach() { return coach; }

    // Fixes "The method setSeatNumber is undefined"
    public void setSeatNumber(int seatNumber) { this.seatNumber = seatNumber; }
    public int getSeatNumber() { return seatNumber; }

    // Fixes "The method setAssignedBerth is undefined"
    public void setAssignedBerth(String assignedBerth) { this.assignedBerth = assignedBerth; }
    public String getAssignedBerth() { return assignedBerth; }

    public String getBerthPreference() { return berthPreference; }
    public void setBerthPreference(String berthPreference) { this.berthPreference = berthPreference; }
}