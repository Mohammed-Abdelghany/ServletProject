package com.item.model;

public class Item {
	private Long id;
	private int total_number;
	private double price;
	private String name;

	
	public Item(int total_number, double price, String name) {
		this.total_number = total_number;
		this.price = price;
		this.name = name;
	}


	public Item(Long id, int total_number, double price, String name) {
		this.id = id;
		this.total_number = total_number;
		this.price = price;
		this.name = name;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public int getTotal_number() {
		return total_number;
	}


	public void setTotal_number(int total_number) {
		this.total_number = total_number;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	
	
	

}
