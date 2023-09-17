package com.example.demo.entities;

import java.util.*;

import jakarta.persistence.CascadeType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	private String email;
	private String password;
	private String about;
	

	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	private List<Post>post=new ArrayList<>();
	
	@ManyToMany
	private Set<Role> role=new HashSet<>();
}
   

