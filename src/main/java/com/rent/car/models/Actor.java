package com.rent.car.models;

import com.rent.car.custom.validator.ValidPassword;
import com.rent.car.security.models.Role;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
    
    @Size(max = 50, message = "Field length should be max 50 characters.")
	@Pattern(regexp = "^[A-Z][a-z]+(?: [A-Z][a-z]+)*$",
	message = "Field should contains only letters with capitalized first and after space.")
	@Column(nullable = false, length = 50)
    private String firstName;
    
    @Size(max = 50, message = "Field length should be max 50 characters.")
	@Pattern(regexp = "^[A-Z][a-z]+(?: [A-Z][a-z]+)*$",
	message = "Field should contains only letters with capitalized first and after space.")
	@Column(nullable = false, length = 50)
    private String lastName;
    
    @Size(min = 8, max = 105, message = "Field length should be between 8 and 105.")
	@Pattern(regexp = "^[a-zA-Z0-9._]+$",
	message = "Field should contains only letters and digits.")
	@Column(nullable = false, unique = true, length = 105)
	private String username;
    
    @ValidPassword
	private String password;
	
	@ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
		@JoinTable(
				name = "actor_role",
				joinColumns = { @JoinColumn(name = "actor_id") },
				inverseJoinColumns = { @JoinColumn(name = "role_id") }
			)
	Set<Role> roles = new HashSet<>();
}
