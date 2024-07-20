package com.inn.rating.ratingrepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inn.rating.entites.Rating;

public interface RatingRepository extends JpaRepository<Rating, String> {

	List<Rating> findByUserId(String userId);

	List<Rating> findByHotelId(String hotelId);
}
