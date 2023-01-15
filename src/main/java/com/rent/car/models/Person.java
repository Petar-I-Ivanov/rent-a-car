package com.rent.car.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class Person {
		
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
	
	@Size(max = 50, message = "Field length should be max 50 characters.")
	@Pattern(regexp = "^$|(^[A-Z][a-z]+(?: [A-Z][a-z]+)*$)",
	message = "Field should contains only letters with capitalized first and after space.")
	@Column(length = 50)
	private String otherName;
	
	@Pattern(regexp = "^Mr\\.$|^Ms\\.$", message = "Pick from the shown.")
	@Column(nullable = false, length = 3)
	private String title;
	
	@Pattern(regexp = "^[A-Z]*$", message = "Should be upper case only.")
	@Column(nullable = false, length = 5)
	private String initials;
	
	@Pattern(regexp = "^$|(^[0-9]{3}-[0-9]{2}-[0-9]{4}$)", message = "Should look like 123-45-6789")
	@Column(length = 11)
	private String socialSecurityNumber;
	
	@Pattern(regexp = "^Male$|^Female$", message = "Pick 'Male' or 'Female'.")
	@Column(nullable = false, length = 6)
	private String gender;
	
	@Pattern(regexp = "^Single$|^Married$", message = "Pick 'Single' or 'Married'.")
	@Column(nullable = false, length = 7)
	private String maritalStatus;
	
	@ManyToOne
	@JoinColumn(name="countryId", insertable=false, updatable=false)
	private Country country;
	private Integer countryId;
	
	@ManyToOne
	@JoinColumn(name="stateId", insertable=false, updatable=false)
	private State state;
	private Integer stateId;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Past(message = "Date should be in the past.")
	private Date dateOfBirth;
	
	@NotBlank(message = "Field cannot be blank.")
	@Size(min = 4, max = 105, message = "Field length should be between 4 and 105.")
	@Pattern(regexp = "^[A-Z][a-z]+(?: [A-Z][a-z]+)*$",
	message = "Field should contains only letters with capitalized first and after space.")
	@Column(nullable = false, length = 105)
	private String city;
	
	@NotBlank(message = "Field cannot be blank.")
	@Size(min = 4, max = 50, message = "Field length should be between 4 and 50.")
	@Column(nullable = false, length = 50)
	private String address;
	
	@Pattern(regexp = "^$|(^[0-9]{10,20}$)", message = "Field should be phone number.")
	@Column(length = 20)
	private String phone;
	
	@NotEmpty(message = "This field is required.")
	@Pattern(regexp = "^[0-9]{10,20}$", message = "Field should be phone number.")
	@Column(nullable = false, length = 20)
	private String mobile;
	
	@NotEmpty(message = "This field is required.")
	@Email(message = "This field should be email.")
	@Column(unique = true, nullable = false)
	private String email;
	
	private String photo;
}
