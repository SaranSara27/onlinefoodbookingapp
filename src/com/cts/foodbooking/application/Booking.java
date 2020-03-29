package com.cts.foodbooking.application;

import java.time.Duration;
import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cts.foodbooking.service.BookingService;

public class Booking {

	final static Logger log = LoggerFactory.getLogger(Booking.class);

	public static void main(String[] args) {
		
		log.debug("Writing restaurant list in dir/output/AllRestaurantDetails.txt is : {}",BookingService.fetchRestaurantDetails());
		
		log.debug("Writing restaurants with rating<4 in dir/output/RatingBasedRestaurants.txt is : {}",BookingService.ratingBasedRestaurant(4));
		
		log.debug("Writing restaurant list(available from 10:00 with duration of 1Hr) in dir/output/TimingBasedRestaurants.txt is : {}"
				,BookingService.timingBasedRestaurant(LocalTime.of(10, 00),Duration.ofHours(1)));
	}

}
