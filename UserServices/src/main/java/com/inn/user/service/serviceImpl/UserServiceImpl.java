package com.inn.user.service.serviceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.inn.user.service.entites.Hotel;
import com.inn.user.service.entites.Rating;
import com.inn.user.service.entites.User;
import com.inn.user.service.exception.ResourceNotFoundEx;
import com.inn.user.service.external.services.HotelService;
import com.inn.user.service.repository.UserRepository;
import com.inn.user.service.service.UserService;

@Service
public class UserServiceImpl implements UserService {

//	 private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private HotelService hotelService;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public User saveUser(User user) {
		String randomId = UUID.randomUUID().toString();
		user.setUserId(randomId);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {

		List<User> users = userRepository.findAll();
		users.forEach(user -> {
			Rating[] ratingOfUser = restTemplate.getForObject("http://RATINGSERVICE/ratings/users/" + user.getUserId(),
					Rating[].class);

			List<Rating> ratings = Arrays.stream(ratingOfUser).toList();

			List<Rating> ratingList = ratings.stream().map(rating -> {
				/*
				 * ResponseEntity<Hotel> forEntity = restTemplate
				 * .getForEntity("http://HOTELSERVICE/hotels/" + rating.getHotelId(),
				 * Hotel.class);
				 */

				Hotel hotel = hotelService.getHotel(rating.getHotelId());
//		            Hotel body = forEntity.getBody();
				rating.setHotel(hotel);
				return rating;
			}).collect(Collectors.toList());

			user.setRatings(ratingList);
		});

		return users;
	}

	@Override
	public User getUser(String userId) {

		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundEx());
		Rating[] ratingOfUser = restTemplate.getForObject("http://RATINGSERVICE/ratings/users/" + user.getUserId(),
				Rating[].class);

		List<Rating> ratings = Arrays.stream(ratingOfUser).toList();

		List<Rating> ratingList = ratings.stream().map(rating -> {
			/*
			 * ResponseEntity<Hotel> forEntity = restTemplate
			 * .getForEntity("http://HOTELSERVICE/hotels/" + rating.getHotelId(),
			 * Hotel.class);
			 */

			Hotel hotel = hotelService.getHotel(rating.getHotelId());
//            Hotel body = forEntity.getBody();
			rating.setHotel(hotel); // Assuming Rating class has a setHotel method
			return rating;
		}).collect(Collectors.toList());

		user.setRatings(ratingList);
		return user;

	}

	@Override
	public String deletebyId(String userId) {
		userRepository.deleteById(userId);
		return "Delete successFully";
	}

	@Override
	public User updateUser(String userId, User user) {
		User existingUser = userRepository.findById(userId).orElse(null);
		if (existingUser != null) {
			existingUser.setName(user.getName());
			existingUser.setEmail(user.getEmail());
			existingUser.setAbout(user.getAbout());
			return userRepository.save(existingUser);
		}
		return null;
	}

}
