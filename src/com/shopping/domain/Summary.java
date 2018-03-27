package com.shopping.domain;

public class Summary {

	private String name;
	private int quantity;
	private int totalPrice;
	private int price;

	public Summary(String name, int quantity, int totalPrice, int price) {
		this.name = name;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
