package com.example.demo.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(ResourceNotFoundException.class)
	private ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex)
	{
		String message=ex.getMessage();
		ApiResponse api=new ApiResponse(message,true);
		return new ResponseEntity<>(api,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class) 
	private ResponseEntity<Map<String,String>>MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex)
	{
	
		Map<String,String>resp=new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((err)->{
			String field=((FieldError)err).getField();
			String message=err.getDefaultMessage();
			resp.put(field, message);
		});
		return new ResponseEntity<>(resp,HttpStatus.BAD_REQUEST);
	}
	
	
}
