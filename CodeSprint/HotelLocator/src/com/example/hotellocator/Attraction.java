package com.example.hotellocator;

public class Attraction {

	private String name;
	private String region;
	private String owner;
	private String manager;
	private String address;
	private String city;
	private String parish;
	private String contact;
	private String theme;
	
	public Attraction(String name, String region, String owner, String manager,
			String address, String city, String parish, String contact,
			String theme) {
		super();
		this.name = name;
		this.region = region;
		this.owner = owner;
		this.manager = manager;
		this.address = address;
		this.city = city;
		this.parish = parish;
		this.contact = contact;
		this.theme = theme;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
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

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}
	
	
	
	
}
