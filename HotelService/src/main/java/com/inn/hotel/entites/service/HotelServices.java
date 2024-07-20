package com.inn.hotel.entites.service;

import java.util.List;

import com.inn.hotel.entites.Hotel;

public interface HotelServices {

	Hotel create(Hotel hotel);
	
	List<Hotel> getAllHotel();
	
	Hotel getHotel(String id);
	
	String deleteHotel(String id);
	
	Hotel UpdateHotel(String id, Hotel hotel);
}
