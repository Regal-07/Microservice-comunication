package com.inn.user.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inn.user.service.entites.User;

public interface UserRepository extends JpaRepository<User, String>{

}
