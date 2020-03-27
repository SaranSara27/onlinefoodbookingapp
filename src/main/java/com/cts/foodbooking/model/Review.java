package com.cts.foodbooking.model;

public class Review {
	private int restaurantId;
	private String reviews;
	
	public Review(int restaurantId, String reviews) {
		super();
		this.restaurantId = restaurantId;
		this.reviews = reviews;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getReviews() {
		return reviews;
	}

	public void setReviews(String reviews) {
		this.reviews = reviews;
	}

	@Override
	public String toString() {
		return "Review [restaurantId=" + restaurantId + ", reviews=" + reviews + "]";
	}
	
	
}
