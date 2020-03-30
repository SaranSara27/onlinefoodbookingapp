package com.cts.foodbooking.dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.cts.foodbooking.model.Dishes;
import com.cts.foodbooking.model.Restaurant;
import com.cts.foodbooking.model.Review;

public class BookingDAO {

	Path restaurantPath = Paths.get("dir", "inputs", "restaurant.csv");
	Path reviewPath = Paths.get("dir", "inputs", "review.csv");
	Path dishesPath = Paths.get("dir", "inputs", "dishes.csv");

	public List<Restaurant> fetchRestaurantDetails() {
		List<Restaurant> resList = null;
		try (Stream<String> bufferData = Files.newBufferedReader(restaurantPath).lines()) {
			resList = bufferData.map(t->getRestaurant(t)).collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resList;
	}

	public String writeRestaurantList(List<Restaurant> resList,String folderPath) {
		try {
			Files.write(Paths.get("dir", folderPath, "AllRestaurantDetails.txt"), resList.toString().getBytes(),
					StandardOpenOption.CREATE);
		} catch (IOException e) {
			e.printStackTrace();
			return "Failure";
		}
		return "Success";
	}
	
	public String writeRatingBasedRestaurant(List<Restaurant> resList,String folderPath) {
		try {
			Files.write(Paths.get("dir", folderPath, "RatingBasedRestaurants.txt"), resList.toString().getBytes(),
					StandardOpenOption.CREATE);
		} catch (IOException e) {
			e.printStackTrace();
			return "Failure";
		}
		return "Success";
	}
	
	public String writeTimingBasedRestaurant(List<Restaurant> resList,String folderPath) {
		try {
			Files.write(Paths.get("dir", folderPath, "TimingBasedRestaurants.txt"), resList.toString().getBytes(),
					StandardOpenOption.CREATE);
		} catch (IOException e) {
			e.printStackTrace();
			return "Failure";
		}
		return "Success";
	}

	private Restaurant getRestaurant(String data) {
		String[] input = data.split(",");
		return new Restaurant(Integer.parseInt(input[0]), input[1], Double.parseDouble(input[2]), getTime(input[3]),
				getTime(input[4]), getReviewList(Integer.parseInt(input[0])),
				getDishesList(Integer.parseInt(input[0])));
	}

	private LocalTime getTime(String input) {
		String[] time = input.split(":");
		return LocalTime.of(Integer.parseInt(time[0]), Integer.parseInt(time[1]), Integer.parseInt(time[2]));
	}

	private List<Review> getReviewList(int restaurantId) {
		List<Review> reviewList = null;
		try (Stream<String> bufferData = Files.newBufferedReader(reviewPath).lines()) {
			reviewList = bufferData.filter(t -> Integer.parseInt(t.split(",")[0].trim()) == restaurantId)
					.map(t -> getReview(t.split(","), restaurantId)).collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}
		return reviewList;

	}

	private Review getReview(String[] input, int restaurantId) {
		if (input.length == 2) {
			return new Review(restaurantId, input[1]);
		} else {
			String review = "";
			for (int i = 1; i < input.length; i++) {
				review += input[i];
			}
			return new Review(restaurantId, review);
		}

	}

	private List<Dishes> getDishesList(int restaurantId) {
		List<Dishes> dishesList = null;
		try (Stream<String> bufferData = Files.newBufferedReader(dishesPath).lines()) {
			dishesList = bufferData.filter(t -> Integer.parseInt(t.split(",")[0].trim()) == restaurantId)
					.map(t -> getDishes(t.split(","), restaurantId)).collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}
		return dishesList;

	}

	private Dishes getDishes(String[] input, int restaurantId) {
		return new Dishes(restaurantId, input[1], Integer.parseInt(input[2]), Integer.parseInt(input[3]));
	}

}
