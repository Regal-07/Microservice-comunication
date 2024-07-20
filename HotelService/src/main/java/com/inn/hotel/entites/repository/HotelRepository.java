package com.inn.hotel.entites.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inn.hotel.entites.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, String> {

}
