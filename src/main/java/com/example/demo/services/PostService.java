package com.example.demo.services;

import java.util.List;


import com.example.demo.payloads.PostDto;

public interface PostService {
	
	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	
	PostDto updatePost(PostDto postDto);
	
	void deletePost(Integer postId);
	
	List<PostDto> getAll();
	
	PostDto findById(Integer postId);
	
	List<PostDto> findByUser(Integer userId);
	List<PostDto> findByCategory(Integer categoryId);

}
