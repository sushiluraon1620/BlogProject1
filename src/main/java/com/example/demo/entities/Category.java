package com.example.demo.entities;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import java.util.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer category_id;
	

	private String category_title;
	
	@OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
	private List<Post>post=new ArrayList<>();
   
}