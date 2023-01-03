package com.rent.car.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contact {
		
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
	
	@Pattern(regexp = "^$|(^[0-9]{10,20}$)", message = "Field should be phone number.")
	@Column(length = 20)
	private String phone;
	
	@Pattern(regexp = "(^[0-9]{10,20}$)", message = "Field should be phone number.")
	@Column(nullable = false, length = 20)
	private String mobile;
	
	@NotNull(message = "This field is required.")
	@Email(message = "This field should be email.")
	@Column(unique = true, nullable = false)
	private String email;
	
	private String remarks;
}
