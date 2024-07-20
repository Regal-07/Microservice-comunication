package com.inn.rating.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inn.rating.entites.Rating;
import com.inn.rating.service.RatingService;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/ratings")
public class RatingController {

	@Autowired
	private RatingService ratingService;

	@PostMapping
	public ResponseEntity<Rating> createRating(@RequestBody Rating rating) {
		Rating createRating = ratingService.Create(rating);
		return ResponseEntity.status(HttpStatus.CREATED).body(createRating);
	}

	@GetMapping
	public ResponseEntity<List<Rating>> getAllRating() {
		List<Rating> allRatings = ratingService.getAllRating();
		return ResponseEntity.ok(allRatings);
	}

	@GetMapping("/{ratingId}")
	public ResponseEntity<Rating> getRating(@PathVariable String ratingId) {
		Rating rat = ratingService.getRating(ratingId);
		return ResponseEntity.ok(rat);
	}

	@DeleteMapping("/{ratingId}")
	public String deleteRating(@PathVariable String ratingId) {
		ratingService.deleteRating(ratingId);
		return "Delete rating successFully !!";
	}

	@PutMapping("/{ratingId}")
	public ResponseEntity<Rating> updateRating(@PathVariable String ratingId, @RequestBody Rating rating) {
		Rating updateRating = ratingService.updateRating(ratingId, rating);
		return ResponseEntity.ok(updateRating);
	}

	@GetMapping("/users/{userId}")
	public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable String userId) {
		return ResponseEntity.ok(ratingService.getRatingByUserId(userId));
	}

	@GetMapping("/hotels/{hotelId}")
	public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable String hotelId) {
		return ResponseEntity.ok(ratingService.getRatingByHotelId(hotelId));
	}
}
