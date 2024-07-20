package com.inn.rating.service;

import java.util.List;

import com.inn.rating.entites.Rating;

public interface RatingService {

	Rating Create(Rating rating);
	
	List<Rating> getAllRating();
	
	Rating getRating(String id);
	
	String deleteRating(String id);
	
	Rating updateRating(String ratingId, Rating rating);
	
	List<Rating> getRatingByUserId(String userId);
	
	List<Rating> getRatingByHotelId(String hotelId);
}
