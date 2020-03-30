package com.cts.foodbooking.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cts.foodbooking.dao.BookingDAO;
import com.cts.foodbooking.model.Dishes;
import com.cts.foodbooking.model.Restaurant;
import com.cts.foodbooking.model.Review;
import com.cts.foodbooking.service.BookingService;

public class BookingServiceTest {

	Logger log = LoggerFactory.getLogger(BookingServiceTest.class);

	@InjectMocks
	BookingService service;

	@Mock
	BookingDAO dao;

	List<Restaurant> resList = new ArrayList<>();

	@Before
	public void setup() {
		Review review1 = new Review(111,"Excellent");
		Review review2 = new Review(222,"good");
		List<Review> reviewList = new ArrayList<>();
		reviewList.add(review1);
		reviewList.add(review2);
		Dishes dishes1= new Dishes(111,"Dosai",50,5);
		Dishes dishes2= new Dishes(222,"Dosai",50,5);
		List<Dishes> dishesList = new ArrayList<>();
		dishesList.add(dishes1);
		dishesList.add(dishes2);
		Restaurant res = new Restaurant(111, "a2b", 3.9, LocalTime.of(9, 00), LocalTime.of(21, 00), reviewList,
				dishesList);
		Restaurant restaurant= new Restaurant(222, "Sangeetha", 3.8, LocalTime.of(8, 00), LocalTime.of(20, 00), reviewList,
				dishesList);
		resList.add(res);
		resList.add(restaurant);
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void fetchRestaurantDetailsTest() {
		when(dao.fetchRestaurantDetails()).thenReturn(resList);
		when(dao.writeRestaurantList(resList,"output")).thenReturn("Success");
		log.info("Testing fetchRestaurantDetails for valid case");
		assertEquals("Success", service.fetchRestaurantDetails());
	}

	@Test
	public void ratingBasedRestaurantTest() {
		when(dao.fetchRestaurantDetails()).thenReturn(resList);
		when(dao.writeRatingBasedRestaurant(resList,"output")).thenReturn("Success");
		log.info("Testing ratingBasedRestaurant for valid case");
		assertEquals("Success", service.ratingBasedRestaurant(4));
	}

	@Test
	public void timingBasedRestaurantTest() {
		when(dao.fetchRestaurantDetails()).thenReturn(resList);
		when(dao.writeTimingBasedRestaurant(resList,"output")).thenReturn("Success");
		log.info("Testing timingBasedRestaurant for valid case");
		assertEquals("Success", service.timingBasedRestaurant(LocalTime.of(10, 00), Duration.ofHours(1)));
	}
	
	@Test
	public void fetchRestaurantDetailsInvalid() {
		when(dao.fetchRestaurantDetails()).thenReturn(null);
		log.info("Testing fetchRestaurantDetails for invalid case");
		assertEquals("Failure", service.fetchRestaurantDetails());
	}

	@Test
	public void ratingBasedRestaurantInvalid() {
		when(dao.fetchRestaurantDetails()).thenReturn(resList);
		log.info("Testing ratingBasedRestaurant for invalid case",service.ratingBasedRestaurant(2));
		assertEquals("Failure", service.ratingBasedRestaurant(2));
	}

	@Test
	public void timingBasedRestaurantInvalid() {
		when(dao.fetchRestaurantDetails()).thenReturn(resList);
		log.info("Testing timingBasedRestaurant for invalid case"+service.timingBasedRestaurant(LocalTime.of(6, 00), Duration.ofHours(1)));
		assertEquals("Failure", service.timingBasedRestaurant(LocalTime.of(6, 00), Duration.ofHours(1)));
	}
}
