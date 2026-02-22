package com.railconnect.trainservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "quotas")
public class Quota {
    @Id
    private String id; // Use String for MongoDB _id
    private String label;
    private String desc;
    private int pct;
    private String color;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getPct() {
		return pct;
	}
	public void setPct(int pct) {
		this.pct = pct;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
    
    
}