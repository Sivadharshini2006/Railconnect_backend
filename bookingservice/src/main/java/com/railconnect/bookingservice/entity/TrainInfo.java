package com.railconnect.bookingservice.entity;

public class TrainInfo {
    private String name;
    private String number;
    private String from;
    private String to;
    private String depTime;
    private String arrTime;
    private String duration;
    private String date;
    private String classType;
    private double price;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }

    public String getFrom() { return from; }
    public void setFrom(String from) { this.from = from; }

    public String getTo() { return to; }
    public void setTo(String to) { this.to = to; }

    public String getDepTime() { return depTime; }
    public void setDepTime(String depTime) { this.depTime = depTime; }

    public String getArrTime() { return arrTime; }
    public void setArrTime(String arrTime) { this.arrTime = arrTime; }

    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getClassType() { return classType; }
    public void setClassType(String classType) { this.classType = classType; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}