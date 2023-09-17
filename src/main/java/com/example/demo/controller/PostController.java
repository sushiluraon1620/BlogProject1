package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.payloads.PostDto;
import com.example.demo.services.PostService;

@RestController
@RequestMapping("/api")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	
	@PostMapping("/user/{userId}/category/{categoryId}/post")
	public ResponseEntity<PostDto> craetPost(@RequestBody PostDto postDto,@PathVariable Integer userId,@PathVariable Integer categoryId)
	{
		
		PostDto post=this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<>(post,HttpStatus.CREATED);
	}
	
	@GetMapping("/{postId}/post")
	public ResponseEntity<PostDto> getById(@PathVariable Integer postId)
	{
		PostDto post=this.postService.findById(postId);
		 return new ResponseEntity<>(post,HttpStatus.OK);
	}
	
	@GetMapping("/allPost")
	public ResponseEntity<List<PostDto>> getAll()
	{
		List<PostDto>post= this.postService.getAll();
		return ResponseEntity.ok(post);
	}
	
	@GetMapping("/findByuser/{userId}")
	public ResponseEntity<List<PostDto>> getByUser(@PathVariable Integer userId)
	{
		return ResponseEntity.ok(this.postService.findByUser(userId));
	}

}
