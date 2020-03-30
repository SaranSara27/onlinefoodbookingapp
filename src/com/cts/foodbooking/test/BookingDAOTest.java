package com.cts.foodbooking.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.nio.file.NoSuchFileException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cts.foodbooking.dao.BookingDAO;
import com.cts.foodbooking.model.Dishes;
import com.cts.foodbooking.model.Restaurant;
import com.cts.foodbooking.model.Review;

public class BookingDAOTest {
	
	Logger log = LoggerFactory.getLogger(BookingDAOTest.class);
	
	@InjectMocks
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
		log.info("Testing fetchRestaurantDetails - DAO for valid case,");
		List<Restaurant> resList=dao.fetchRestaurantDetails();
		log.debug("resList : {}",resList);
		assertEquals(2, resList.size());
	}
	
	@Test
	public void writeRatingBasedRestaurantTest() {
		log.info("Testing writeRatingBasedRestaurant - DAO for valid case,");
		assertEquals("Success", dao.writeRatingBasedRestaurant(resList,"test"));
	}
	
	@Test
	public void writeTimingBasedRestaurantTest() {
		log.info("Testing writeTimingBasedRestaurant - DAO for valid case,");
		assertEquals("Success", dao.writeTimingBasedRestaurant(resList,"test"));
	}
	

}
