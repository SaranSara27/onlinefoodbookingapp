package com.cts.foodbooking.test;

import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.time.LocalTime;

import org.junit.Test;

import com.cts.foodbooking.service.BookingService;

public class BookingServiceTest {
	
	@Test
	public void fetchRestaurantDetailsTest() {
		assertEquals("Success", BookingService.fetchRestaurantDetails());
	}
	
	@Test
	public void ratingBasedRestaurantTest() {
		assertEquals("Success", BookingService.ratingBasedRestaurant(4));
	}
	
	@Test
	public void timingBasedRestaurantTest() {
		assertEquals("Success", BookingService.timingBasedRestaurant(LocalTime.of(10, 00),Duration.ofHours(1)));
	}
}
