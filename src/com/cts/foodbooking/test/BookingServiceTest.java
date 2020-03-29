package com.cts.foodbooking.test;

import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;
//import org.mockito.MockitoAnnotations;

import com.cts.foodbooking.service.BookingService;

public class BookingServiceTest {
	
	/*
	 * @Before public void setup() { MockitoAnnotations.initMocks(this); }
	 */

	@Test
	public void fetchRestaurantDetailsTest() {
		assertEquals("Success", BookingService.fetchRestaurantDetails());
	}
	
	@Test
	public void ratingBasedRestaurantTest() {
		assertEquals("Success", BookingService.ratingBasedRestaurant(3));
	}
	
	@Test
	public void timingBasedRestaurantTest() {
		assertEquals("Success", BookingService.timingBasedRestaurant(LocalTime.of(12, 00),Duration.ofHours(1)));
	}
}
