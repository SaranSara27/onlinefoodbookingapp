package com.cts.foodbooking.model;

import java.time.LocalTime;
import java.util.List;

public class Restaurant {

	private int restaurantId;
	private String restaurantName;
	private double ratings;
	private LocalTime openingTime,closingTime;
	private List<Review> reviews;
	private List<Dishes> dishes;
	
	public Restaurant(int restaurantId, String restaurantName, double ratings, LocalTime openingTime,
			LocalTime closingTime, List<Review> reviews, List<Dishes> dishes) {
		super();
		this.restaurantId = restaurantId;
		this.restaurantName = restaurantName;
		this.ratings = ratings;
		this.openingTime = openingTime;
		this.closingTime = closingTime;
		this.reviews = reviews;
		this.dishes = dishes;
	}
	
	public int getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public double getRatings() {
		return ratings;
	}
	public void setRatings(double ratings) {
		this.ratings = ratings;
	}
	public LocalTime getOpeningTime() {
		return openingTime;
	}
	public void setOpeningTime(LocalTime openingTime) {
		this.openingTime = openingTime;
	}
	public LocalTime getClosingTime() {
		return closingTime;
	}
	public void setClosingTime(LocalTime closingTime) {
		this.closingTime = closingTime;
	}
	public List<Review> getReviews() {
		return reviews;
	}
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	public List<Dishes> getDishes() {
		return dishes;
	}
	public void setDishes(List<Dishes> dishes) {
		this.dishes = dishes;
	}

	@Override
	public String toString() {
		return "Restaurant [restaurantId=" + restaurantId + ", restaurantName=" + restaurantName + ", ratings="
				+ ratings + ", openingTime=" + openingTime + ", closingTime=" + closingTime + ", reviews=" + reviews
				+ ", dishes=" + dishes + "]";
	}
	
	
	
}
