package com.rent.car.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Client {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "Field cannot be blank.")
	@Size(min = 4, max = 105, message = "Field length should be between 4 and 105.")
	@Column(nullable = false, unique = true, length = 105)
	private String name;
	
	@NotBlank(message = "Field cannot be blank.")
	@Size(min = 4, max = 50, message = "Field length should be between 4 and 50.")
	@Column(nullable = false, unique = true, length = 50)
	private String address;
	
	@NotBlank(message = "Field cannot be blank.")
	@Size(min = 4, max = 105, message = "Field length should be between 4 and 105.")
	@Pattern(regexp = "^[A-Z][a-z]+(?: [A-Z][a-z]+)*$",
	message = "Field should contains only letters with capitalized first and after space.")
	@Column(nullable = false, length = 105)
	private String city;
	
	@Pattern(regexp = "^$|(^[0-9]{10,20}$)", message = "Field should be phone number.")
	@Column(length = 20)
	private String phone;
	
	@NotNull(message = "This field is required.")
	@Pattern(regexp = "^[0-9]{10,20}$", message = "Field should be phone number.")
	@Column(nullable = false, length = 20)
	private String mobile;
	
	@Size(max = 60, message = "Field length should be max 60.")
	@Pattern(regexp = "^$|(^(https:|http:|www\\.)\\S*)", message = "Field should be url.")
	@Column(length = 60)
	private String website;
	
	@NotNull(message = "This field is required.")
	@Email(message = "This field should be email.")
	@Column(unique = true, nullable = false)
	private String email;
	
	@ManyToOne
	@JoinColumn(name="countryId", insertable=false, updatable=false)
	private Country country;
	private int countryId;
	
	@ManyToOne
	@JoinColumn(name="stateId", insertable=false, updatable=false)
	private State state;	
	private int stateId;
	
	private String details;
	
	@OneToMany(mappedBy = "client")
	private List<Invoice> invoices;
	
	@OneToMany(mappedBy = "client")
	private List<VehicleHire> vehicleHires;
}
