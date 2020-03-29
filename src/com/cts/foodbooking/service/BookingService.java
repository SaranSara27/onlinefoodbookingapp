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
	
	final static Logger log = LoggerFactory.getLogger(Booking.class);

	public static String fetchRestaurantDetails() {
		List<Restaurant> resList = BookingDAO.fetchRestaurantDetails();
		log.debug("resList in fetchRestaurantDetails ={}",resList);
		if (resList == null) {
			return "failure";
		}
		return BookingDAO.writeRestaurantList(resList);
	}

	public static String ratingBasedRestaurant(int rating) {
		List<Restaurant> list = BookingDAO.fetchRestaurantDetails().stream().filter(r -> r.getRatings() < rating)
				.collect(Collectors.toList());
		log.debug("resList in ratingBasedRestaurant={}",list);
		if (list == null) {
			return "failure";
		}
		return BookingDAO.writeRatingBasedRestaurant(list);
	}

	public static String timingBasedRestaurant(LocalTime startTime, Duration frequency) {

		List<Restaurant> restaurantList = BookingDAO.fetchRestaurantDetails().stream()
				.filter(t -> filterTime(t, startTime, frequency)).collect(Collectors.toList());
		log.debug("resList in timingBasedRestaurant={}",restaurantList);
		if (restaurantList == null) {
			return "failure";
		}
		return BookingDAO.writeTimingBasedRestaurant(restaurantList);

	}

	private static boolean filterTime(Restaurant res, LocalTime startTime, Duration frequency) {
		log.debug("startTime {}",startTime);
		LocalTime endTime = startTime.plusHours(frequency.toHours());
		log.debug("endTime {}",endTime);
		if ((res.getOpeningTime().equals(startTime) || res.getOpeningTime().isBefore(startTime))
				&& (res.getClosingTime().equals(endTime) || res.getClosingTime().isAfter(endTime))) {
			return true;
		}
		return false;

	}
}
