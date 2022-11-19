package com.rent.car.models;

import com.rent.car.security.models.Role;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Actor {
		
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
    private String firstName;
    private String lastName;
	private String username;
	private String password;
	
	@ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
		@JoinTable(
				name = "actor_role",
				joinColumns = { @JoinColumn(name = "actor_id") },
				inverseJoinColumns = { @JoinColumn(name = "role_id") }
			)
	Set<Role> roles = new HashSet<>();
}
