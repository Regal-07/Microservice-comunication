package com.inn.hotel.entites.serviceImpl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inn.hotel.entites.Hotel;
import com.inn.hotel.entites.exception.ResourceNotFound;
import com.inn.hotel.entites.repository.HotelRepository;
import com.inn.hotel.entites.service.HotelServices;

@Service
public class HotelServiceImpl implements HotelServices {

	@Autowired
	private HotelRepository hotelRepository;

	@Override
	public Hotel create(Hotel hotel) {
		String hotelId = UUID.randomUUID().toString();
		hotel.setId(hotelId);
		return hotelRepository.save(hotel);
	}

	@Override
	public List<Hotel> getAllHotel() {
		return hotelRepository.findAll();
	}

	@Override
	public Hotel getHotel(String id) {
		return hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFound("hotel with given id not avalable"));
	}

	@Override
	public String deleteHotel(String id) {
		hotelRepository.deleteById(id);
		return "Hotel delete succesFully";
	}

	@Override
	public Hotel UpdateHotel(String id, Hotel hotel) {
		Hotel hotelId = hotelRepository.findById(id).orElse(null);
		if (hotelId != null) {
			hotelId.setName(hotel.getName());
			hotelId.setLocation(hotel.getLocation());
			hotelId.setAbout(hotel.getAbout());
			return hotelRepository.save(hotelId);
		}

		return null;
	}

}
