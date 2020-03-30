package com.cts.foodbooking.service;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cts.foodbooking.application.Booking;
import com.cts.foodbooking.dao.BookingDAO;
import com.cts.foodbooking.model.Restaurant;


public class BookingService {
	
	Logger log = LoggerFactory.getLogger(Booking.class);
	
	BookingDAO dao = new BookingDAO();

	public String fetchRestaurantDetails() {
		List<Restaurant> resList = dao.fetchRestaurantDetails();
		log.debug("resList in fetchRestaurantDetails ={}",resList);
		if (resList == null || resList.isEmpty()) {
			return "Failure";
		}
		return dao.writeRestaurantList(resList,"output");
	}

	public String ratingBasedRestaurant(int rating) {
		List<Restaurant> list = dao.fetchRestaurantDetails().stream().filter(r -> r.getRatings() < rating)
				.collect(Collectors.toList());
		log.debug("resList in ratingBasedRestaurant={}",list);
		if (list == null || list.isEmpty()) {
			return "Failure";
		}
		return dao.writeRatingBasedRestaurant(list,"output");
	}

	public String timingBasedRestaurant(LocalTime startTime, Duration frequency) {

		List<Restaurant> restaurantList = dao.fetchRestaurantDetails().stream()
				.filter(t -> filterTime(t, startTime, frequency)).collect(Collectors.toList());
		log.debug("resList in timingBasedRestaurant={}",restaurantList);
		if (restaurantList == null || restaurantList.isEmpty()) {
			return "Failure";
		}
		return dao.writeTimingBasedRestaurant(restaurantList,"output");

	}

	private boolean filterTime(Restaurant res, LocalTime startTime, Duration frequency) {
		LocalTime endTime = startTime.plusHours(frequency.toHours());
		if ((res.getOpeningTime().equals(startTime) || res.getOpeningTime().isBefore(startTime))
				&& (res.getClosingTime().equals(endTime) || res.getClosingTime().isAfter(endTime))) {
			return true;
		}
		return false;

	}
}
