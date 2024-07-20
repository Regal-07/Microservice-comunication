package com.inn.user.service.service;

import java.util.List;

import com.inn.user.service.entites.User;

public interface UserService {

	User saveUser(User user);

	List<User> getAllUser();

	User getUser(String userId);

	String deletebyId(String userId);

	User updateUser(String userId ,User user);
}
