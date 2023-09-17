package com.example.demo.controller;

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

import com.example.demo.payloads.UserDto;
import com.example.demo.services.UserService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
     @PostMapping("/")
	private ResponseEntity<UserDto> createUser( @Valid @RequestBody UserDto userDto)
	{
		UserDto createduser=this.userService.createUser(userDto);
		return new ResponseEntity<>(createduser,HttpStatus.CREATED);
	}
    
     @PutMapping("/{userId}")
     private ResponseEntity<UserDto>updateUser(@Valid @RequestBody UserDto userDto,@PathVariable Integer userId)
     {
    	  UserDto updatedUser=this.userService.updateUser(userDto, userId);
    	  
    	  return new ResponseEntity<>(updatedUser,HttpStatus.OK);
     }
     
     @GetMapping("/{userId}")
     private ResponseEntity<UserDto> getUserById(@PathVariable Integer userId)
     {
     UserDto user=this.userService.getUserById(userId);
     return ResponseEntity.ok(user);
     }
     @GetMapping("/")
     private ResponseEntity<List<UserDto>> getAllUsers()
     {
     
     return ResponseEntity.ok(this.userService.getAllUsers());
     }
     
     @DeleteMapping("/{userId}")
     private ResponseEntity<String> deleteUser(@PathVariable Integer userId)
     {
    	 this.userService.deleteUser(userId);
    	 return ResponseEntity.ok("user deleted succesfully");
     }

}
