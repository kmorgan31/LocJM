package com.example.hotellocator;

import java.util.Date;

public class Event {
	
	private String name;
	private Date date;
	private String address;
	private String city;
	private String parish;
	private String region;
	private String theme;
	
	public Event(String name, Date date, String address, String city,
			String parish, String region, String theme) {
		super();
		this.name = name;
		this.date = date;
		this.address = address;
		this.city = city;
		this.parish = parish;
		this.region = region;
		this.theme = theme;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getParish() {
		return parish;
	}

	public void setParish(String parish) {
		this.parish = parish;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}
	
	

}
