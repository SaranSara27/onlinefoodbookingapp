package com.cts.foodbooking.model;

public class Dishes {
	
	private int restaurantId;
	private String dishName;
	private int price;
	private int discount;
	
	public Dishes(int restaurantId, String dishName, int price, int discount) {
		super();
		this.restaurantId = restaurantId;
		this.dishName = dishName;
		this.price = price;
		this.discount = discount;
	}
	public int getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}
	public String getDishName() {
		return dishName;
	}
	public void setDishName(String dishName) {
		this.dishName = dishName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	
	@Override
	public String toString() {
		return "Dishes [restaurantId=" + restaurantId + ", dishName=" + dishName + ", price=" + price + ", discount="
				+ discount + "]";
	} 
	
	

}
