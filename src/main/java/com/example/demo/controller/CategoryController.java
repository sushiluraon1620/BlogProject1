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

import com.example.demo.payloads.CategoryDto;
import com.example.demo.services.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto)
	{
		CategoryDto category=this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<>(category,HttpStatus.CREATED);
	}
	
	@PutMapping("/{category_id}")
	public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto,@PathVariable Integer category_id)
	{
		CategoryDto c=this.categoryService.updateCategory(categoryDto, category_id);
		
		return new ResponseEntity<>(c,HttpStatus.OK);
	}
	
	@DeleteMapping("/{category_id}")
	public ResponseEntity<String> deleteCategory(@PathVariable Integer category_id )
	{
		 this.categoryService.deleteCategory(category_id);
		 return  ResponseEntity.ok("Category delelted successfully");
	}
	
	@GetMapping("/{category_id}")
	public ResponseEntity<CategoryDto> getById(@PathVariable Integer category_id)
	{
		
		CategoryDto c=this.categoryService.getCategoryById(category_id);
		return new ResponseEntity<>(c,HttpStatus.FOUND);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAll()
	{
//		List<CategoryDto> c=this.categoryService.getAllCategory();
		return ResponseEntity.ok(this.categoryService.getAllCategory());
	}
	

}
