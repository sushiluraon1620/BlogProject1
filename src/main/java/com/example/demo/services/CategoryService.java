package com.example.demo.services;

import java.util.List;


import org.springframework.stereotype.Service;

import com.example.demo.payloads.CategoryDto;
import com.example.demo.payloads.UserDto;

@Service
public interface CategoryService {
	
	CategoryDto createCategory(CategoryDto categoryDto);
	CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryDto_id);
	CategoryDto getCategoryById(Integer categoryDto_id);
	List<CategoryDto>getAllCategory();
	void deleteCategory(Integer categoryDto_id);

}
