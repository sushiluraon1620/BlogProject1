package com.example.demo.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Category;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.payloads.CategoryDto;
import com.example.demo.payloads.UserDto;
import com.example.demo.repositories.CategoryRepo;
import com.example.demo.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	 @Autowired
	 private CategoryRepo categoryRepo;
	 
	 @Autowired
	 private ModelMapper modelMapper;
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		// TODO Auto-generated method stub
		
		Category category=this.modelMapper.map(categoryDto, Category.class);
		
		this.categoryRepo.save(category);
		
		return this.modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryDto_id) {
		// TODO Auto-generated method stub
		Category c=this.categoryRepo.findById(categoryDto_id).orElseThrow(()->new ResourceNotFoundException("Category","category_id",categoryDto_id));
		
		c.setCategory_id(categoryDto.getCategory_id());
		c.setCategory_title(categoryDto.getCategory_title());
		
		Category save=this.categoryRepo.save(c);
		
		return this.modelMapper.map(save, CategoryDto.class);
	}

	@Override
	public CategoryDto getCategoryById(Integer categoryDto_id) {
		// TODO Auto-generated method stub
		Category c=this.categoryRepo.findById(categoryDto_id).orElseThrow(()->new ResourceNotFoundException("Category","category_id",categoryDto_id));
		
		return this.modelMapper.map(c, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		// TODO Auto-generated method stub
		List<Category>c=this.categoryRepo.findAll();
//		List<UserDto> userDto=users.stream().map(user->this.UserToUserDto(user)).collect(Collectors.toList());

		List<CategoryDto> res=c.stream().map(category->this.modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
		return res;
	}

	@Override
	public void deleteCategory(Integer categoryDto_id) {
		// TODO Auto-generated method stub
		
		Category c=this.categoryRepo.findById(categoryDto_id).orElseThrow(()->new ResourceNotFoundException("Category","category_id",categoryDto_id));
		this.categoryRepo.deleteById(categoryDto_id);
	}
 

}
