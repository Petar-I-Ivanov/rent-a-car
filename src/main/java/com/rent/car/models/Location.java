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
public class Location {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "Field cannot be blank.")
	@Size(min = 4, max = 105, message = "Field length should be between 4 and 105.")
	@Pattern(regexp = "^[A-Z][a-z]+(?: [A-Z][a-z]+)*$",
	message = "Field should contains only letters with capitalized first and after space.")
	@Column(nullable = false, unique = true, length = 105)
	private String description;
	
	@ManyToOne
	@JoinColumn(name="countryId", insertable=false, updatable=false)
	private Country country;
	@NotNull(message = "Field cannot be null.")
	private int countryId;
	
	@ManyToOne
	@JoinColumn(name="stateId", insertable=false, updatable=false)
	private State state;
	@NotNull(message = "Field cannot be null.")
	private int stateId;
	
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
	
	private String details;
	
	@OneToMany(mappedBy = "currentLocation")
	private List<Vehicle> vehicles;
	
	@OneToMany(mappedBy = "location")
	private List<VehicleHire> vehicleHires;
	
	@OneToMany(mappedBy = "fromLocation")
	private List<VehicleMovement> fromVehicleMovements;
	
	@OneToMany(mappedBy = "toLocation")
	private List<VehicleMovement> toVehicleMovements;
}
