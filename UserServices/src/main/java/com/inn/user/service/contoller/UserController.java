package com.inn.user.service.contoller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inn.user.service.entites.Hotel;
import com.inn.user.service.entites.Rating;
import com.inn.user.service.entites.User;
import com.inn.user.service.service.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/users")
public class UserController {

	private Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User u1 = userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(u1);
	}

	@GetMapping("/{userId}")
	@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
	public ResponseEntity<User> getUserById(@PathVariable String userId) {
		User u1 = userService.getUser(userId);
		return ResponseEntity.ok(u1);
	}

	public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex) {
		logger.info("FallBack is executed because rating setvice is down :", ex.getMessage());
		User user = User.builder().email("dummy@email.com").name("dummy")
				.about("This is dummy user beacuse ratging service is down").userId("15645").build();
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@GetMapping
	@CircuitBreaker(name = "ratingHotelBreakerAll", fallbackMethod = "ratingHotelFallbackAll")
	public ResponseEntity<List<User>> getAllUser() {
		List<User> allUser = userService.getAllUser();
		return ResponseEntity.ok(allUser);
	}

	public ResponseEntity<List<User>> ratingHotelFallbackAll(Exception ex) {
		logger.info("Fallback is executed because the rating service is down: {}", ex.getMessage());

		User user = new User();
		user.setUserId("dummyUserId");
		user.setName("Dummy User");
		user.setEmail("dummy@user.com");
		user.setAbout("This is a dummy user because the service is down");
		user.setRatings(List.of(new Rating("dummyRatingId", "dummyUserId", "dummyHotelId", 0,
				"This is a dummy rating because the service is down", new Hotel("dummyHotelId", "dummyHotel",
						"dummyLocation", "This is dummy hotel because the service is down"))));

		return new ResponseEntity<>(List.of(user), HttpStatus.OK);
	}

	@DeleteMapping("/{userId}")
	public String deletebyId(@PathVariable String userId) {
		userService.deletebyId(userId);
		return "Delete User SuccessFully : " + userId;
	}

	@PutMapping("/{userId}")
	public ResponseEntity<User> updateUser(@PathVariable String userId, @RequestBody User user) {
		User u1 = userService.updateUser(userId, user);
		return ResponseEntity.ok(u1);
	}

}
