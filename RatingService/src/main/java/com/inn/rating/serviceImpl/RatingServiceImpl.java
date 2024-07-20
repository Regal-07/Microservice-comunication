package com.inn.rating.serviceImpl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inn.rating.entites.Rating;
import com.inn.rating.ratingrepo.RatingRepository;
import com.inn.rating.service.RatingService;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	private RatingRepository ratingRepository;

	@Override
	public Rating Create(Rating rating) {
		String RatingId = UUID.randomUUID().toString();
		rating.setId(RatingId);
		return ratingRepository.save(rating);
	}

	@Override
	public List<Rating> getAllRating() {
		return ratingRepository.findAll();
	}

	@Override
	public Rating getRating(String id) {
		return ratingRepository.findById(id).orElse(null);
	}

	@Override
	public String deleteRating(String id) {
		ratingRepository.deleteById(id);
		return "Delete Id SuceessFully";
	}

	@Override
	public Rating updateRating(String ratingId, Rating rating) {
		Rating updateRating = ratingRepository.findById(ratingId).orElse(null);
		if (updateRating != null) {
			updateRating.setRating(rating.getRating());
			updateRating.setFeedback(rating.getFeedback());
			updateRating.setHotelId(rating.getHotelId());
			updateRating.setUserId(rating.getUserId());

			return ratingRepository.save(updateRating);
		}
		return null;
	}

	@Override
	public List<Rating> getRatingByUserId(String userId) {
		return ratingRepository.findByUserId(userId);
	}

	@Override
	public List<Rating> getRatingByHotelId(String hotelId) {
		return ratingRepository.findByHotelId(hotelId);
	}

}
