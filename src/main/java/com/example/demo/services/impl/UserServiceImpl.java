package com.example.demo.services.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;

import com.example.demo.entities.User;
import com.example.demo.payloads.UserDto;
import com.example.demo.repositories.UserRepo;
import com.example.demo.services.UserService;

@Service
public class UserServiceImpl implements UserService {
       @Autowired
	  private UserRepo userRepo;
       @Autowired
       private ModelMapper modelMapper;
	@Override
	public UserDto createUser(UserDto userDto) {
		// TODO Auto-generated method stub
		
		User user=this.UserDtoToUser(userDto);
		User savedUser=this.userRepo.save(user);
		return this.UserToUserDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		// TODO Auto-generated method stub
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","id",userId));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
	 
		User save=this.userRepo.save(user);
		
		return this.UserToUserDto(save);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		// TODO Auto-generated method stub
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","id",userId));
		return this.UserToUserDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
		List<User> users=this.userRepo.findAll();
		List<UserDto> userDto=users.stream().map(user->this.UserToUserDto(user)).collect(Collectors.toList());
		return userDto;
	}

	@Override
	public void deleteUser(Integer id) {
		// TODO Auto-generated method stub
		User user=this.userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("user","id",id));
         this.userRepo.deleteById(id);
	}
	
	private User UserDtoToUser(UserDto userDto)
	{
		User user=this.modelMapper.map(userDto, User.class);
//	     user.setId(userDto.getId());
//	     user.setAbout(userDto.getAbout());
//	     user.setEmail(userDto.getEmail());
//	     user.setName(userDto.getName());
//	     user.setPassword(userDto.getPassword());
	     return user;
	}
	private UserDto UserToUserDto(User user)
	{
//		UserDto userDto=new UserDto();
//		userDto.setId(user.getId());
//		userDto.setAbout(user.getAbout());
//		userDto.setEmail(user.getEmail());
//		userDto.setName(user.getName());
//		userDto.setPassword(user.getPassword());
		UserDto userDto=this.modelMapper.map(user, UserDto.class);
	     return userDto;
	}

}
