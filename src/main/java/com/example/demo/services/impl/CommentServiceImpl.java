package com.example.demo.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Comment;
import com.example.demo.entities.Post;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.payloads.CommentDto;
import com.example.demo.repositories.CommentRepo;
import com.example.demo.repositories.PostRepo;
import com.example.demo.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService{

	 @Autowired
	 private PostRepo postRepo;
	 
	 @Autowired
	 private CommentRepo commentRepo;
	 
	 @Autowired
	 private ModelMapper modelMapper;
	@Override
	public CommentDto createComment(CommentDto comment, Integer postId) {
		// TODO Auto-generated method stub
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postId", postId));
		Comment com=this.modelMapper.map(comment, Comment.class);
		com.setPost(post);
		Comment saved =this.commentRepo.save(com);
		return this.modelMapper.map(saved, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		// TODO Auto-generated method stub
		
		Comment com=this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","commentId", commentId));
		this.commentRepo.deleteById(commentId);
	}

}
