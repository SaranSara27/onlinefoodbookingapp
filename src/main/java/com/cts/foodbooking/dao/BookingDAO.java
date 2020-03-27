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
	
	static Path restaurantPath = Paths.get("dir","inputs","restaurant.csv");
	static Path reviewPath = Paths.get("dir","inputs","review.csv");
	static Path dishesPath = Paths.get("dir","inputs","dishes.csv");
	
	public static void fetchRestaurantDetails(){
		try(Stream<String> bufferData = Files.newBufferedReader(restaurantPath).lines()){
			List<Restaurant> resList = bufferData.map(BookingDAO::getRestaurant)
			.collect(Collectors.toList());
			//Files.write(Paths.get("dir","copyGadget.csv"),resList.toString() , StandardOpenOption.CREATE);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static Restaurant getRestaurant(String  data) {
		String[] input = data.split(",");
		return new Restaurant(Integer.parseInt(input[0]),input[1],Double.parseDouble(input[2])
				,getTime(input[3]),getTime(input[4])
				,getReviewList(Integer.parseInt(input[0])),getDishesList(Integer.parseInt(input[0])));
	}
	
	private static LocalTime getTime(String input) {
		String[] time=input.split(":");
		return LocalTime.of(Integer.parseInt(time[0]), Integer.parseInt(time[1]), Integer.parseInt(time[2]));
	}
	
	private static List<Review> getReviewList(int restaurantId){
		List<Review> reviewList=null;
		try(Stream<String> bufferData = Files.newBufferedReader(reviewPath).lines()){
			reviewList = bufferData.filter(t->Integer.parseInt(t.split(",")[0].trim())==restaurantId)
					.map(t->getReview(t.split(","), restaurantId))
			.collect(Collectors.toList());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return reviewList;
		
	}
	
	private static Review getReview(String[]  input,int restaurantId) {
		if(input.length==2) {
			return new Review(restaurantId,input[1]);
		}
		else {
			String review="";
			for(int i=1;i<input.length;i++) {
				review+=input[i];
			}
			return new Review(restaurantId,review);
		}
		
	}
	
	private static List<Dishes> getDishesList(int restaurantId){
		List<Dishes> dishesList=null;
		try(Stream<String> bufferData = Files.newBufferedReader(dishesPath).lines()){
			dishesList = bufferData.filter(t->Integer.parseInt(t.split(",")[0].trim())==restaurantId)
					.map(t->getDishes(t.split(","), restaurantId))
			.collect(Collectors.toList());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dishesList;
		
	}
	
	private static Dishes getDishes(String[]  input,int restaurantId) {
		return new Dishes(restaurantId,input[1],Integer.parseInt(input[2]),Integer.parseInt(input[3]));
	}

}
