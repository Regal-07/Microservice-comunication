package com.inn.rating.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_rating")
public class Rating {

	@Id
	private String Id;

	private String userId;

	private String hotelId;

	private int rating;

	private String feedback;
	

}
