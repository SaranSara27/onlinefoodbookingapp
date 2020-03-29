package com.cts.foodbooking.service;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import com.cts.foodbooking.dao.BookingDAO;
import com.cts.foodbooking.model.Restaurant;

public class BookingService {

	public static String fetchRestaurantDetails() {
		List<Restaurant> resList = BookingDAO.fetchRestaurantDetails();
		if (resList == null) {
			return "failure";
		}
		return BookingDAO.writeRestaurantList(resList);
	}

	public static String ratingBasedRestaurant(int rating) {
		List<Restaurant> resList = BookingDAO.fetchRestaurantDetails().stream().filter(r -> r.getRatings() < 4)
				.collect(Collectors.toList());
		if (resList == null) {
			return "failure";
		}
		return BookingDAO.writeRatingBasedRestaurant(resList);
	}

	public static String timingBasedRestaurant(LocalTime startTime, Duration frequency) {

		List<Restaurant> resList = BookingDAO.fetchRestaurantDetails().stream()
				.filter(t -> filterTime(t, startTime, frequency)).collect(Collectors.toList());
		if (resList == null) {
			return "failure";
		}
		return BookingDAO.writeTimingBasedRestaurant(resList);

	}

	private static boolean filterTime(Restaurant res, LocalTime startTime, Duration frequency) {
		LocalTime endTime = startTime.plusHours(frequency.toHours());
		if ((res.getOpeningTime().equals(startTime) || res.getOpeningTime().isBefore(startTime))
				&& (res.getClosingTime().equals(endTime) || res.getClosingTime().isAfter(endTime))) {
			return true;
		}
		return false;

	}
}
