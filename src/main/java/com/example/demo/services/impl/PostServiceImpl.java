package com.example.demo.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Category;
import com.example.demo.entities.Post;
import com.example.demo.entities.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.payloads.PostDto;
import com.example.demo.repositories.CategoryRepo;
import com.example.demo.repositories.PostRepo;
import com.example.demo.repositories.UserRepo;
import com.example.demo.services.PostService;
@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		
		// TODO Auto-generated method stub
		
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "userId", userId));
		
	    Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "categoryId", categoryId));
		
	    Post post=this.modelMapper.map(postDto,Post.class);
	    post.setAddedDate(new Date());
	    post.setUser(user);
	    post.setCategory(category);
	    
	    Post saved=this.postRepo.save(post);
		
		return this.modelMapper.map(saved,PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto) {
		// TODO Auto-generated method stub
		
		Post post=this.modelMapper.map(postDto, Post.class);
	    Post saved=this.postRepo.save(post);
		return this.modelMapper.map(saved, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		// TODO Auto-generated method stub
		
	Post deleted= this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("User", "userId", postId));
     this.postRepo.delete(deleted);
	}

	@Override
	public List<PostDto> getAll() {
		// TODO Auto-generated method stub
		
		List<Post> posts=this.postRepo.findAll();
		List<PostDto>postDto=posts.stream().map(post->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDto;
	}

	@Override
	public PostDto findById(Integer postId) {
		// TODO Auto-generated method stub
		
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","PostId", postId));
		
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> findByUser(Integer userId) {
		// TODO Auto-generated method stub
		
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","userId", userId));
		List<Post> posts=this.postRepo.findByUser(user);
		return posts.stream().map(post->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<PostDto> findByCategory(Integer categoryId) {
		// TODO Auto-generated method stub

		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("User","userId", categoryId));
		List<Post> posts=this.postRepo.findByCategory(category);
		return posts.stream().map(post->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
	}

}
