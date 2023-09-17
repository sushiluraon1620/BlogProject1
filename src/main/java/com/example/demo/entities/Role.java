package com.example.demo.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer roleId;
	private String roleName;
	
	@ManyToMany
	@JoinTable(name="User_ROle", joinColumns =@JoinColumn(name="role",referencedColumnName = "roleId"),
	inverseJoinColumns=@JoinColumn(name="user",referencedColumnName = "id"))
	Set<User> user=new HashSet<>();

}
