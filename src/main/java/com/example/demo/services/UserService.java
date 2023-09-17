package com.example.demo.services;

import com.example.demo.payloads.UserDto;
import java.util.*;

import org.springframework.stereotype.Service;

@Service
public interface UserService {

	
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user,Integer userId);
	UserDto getUserById(Integer userId);
	List<UserDto>getAllUsers();
	void deleteUser(Integer id);
}
