package com.inn.hotel.entites.controller;

import java.util.List;

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

import com.inn.hotel.entites.Hotel;
import com.inn.hotel.entites.service.HotelServices;

@RestController
@RequestMapping("/hotels")
public class HotelController {

	@Autowired
	private HotelServices hotelServices;
	
	@PostMapping
	public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
		Hotel hotelCreate = hotelServices.create(hotel);
		return ResponseEntity.status(HttpStatus.CREATED).body(hotelCreate);
	}
	
	@GetMapping
	public ResponseEntity<List<Hotel>> getAllHotel(){
		List<Hotel> allHotel = hotelServices.getAllHotel();
		return ResponseEntity.ok(allHotel);
	}
	
	@GetMapping("/{hotelId}")
	public ResponseEntity<Hotel> getHotelById(@PathVariable String hotelId){
		Hotel getHotel = hotelServices.getHotel(hotelId);
		return ResponseEntity.ok(getHotel);
	}
	
	@DeleteMapping("/{hotelId}")
	public String deleteById(@PathVariable String hotelId) {
		hotelServices.deleteHotel(hotelId);
		return "Hotel delete SuccessFull";
	}
	
	@PutMapping("/{hotelId}")
	public ResponseEntity<Hotel> updateHotel(@PathVariable String hotelId, @RequestBody Hotel hotel){
		Hotel updateHotel = hotelServices.UpdateHotel(hotelId, hotel);
		return ResponseEntity.ok(updateHotel);
	}
}
