package com.rent.car.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
	
	private String description;
	private String details;
	
	@ManyToOne
	@JoinColumn(name="countryId", insertable=false, updatable=false)
	private Country country;
	private int countryId;
	
	@ManyToOne
	@JoinColumn(name="stateId", insertable=false, updatable=false)
	private State state;
	private int stateId;
		
	private String city;
	private String address;
	
	@OneToMany(mappedBy = "currentLocation")
	private List<Vehicle> vehicles;
	
	@OneToMany(mappedBy = "location")
	private List<VehicleHire> vehicleHires;
	
	@OneToMany(mappedBy = "fromLocation")
	private List<VehicleMovement> fromVehicleMovements;
	
	@OneToMany(mappedBy = "toLocation")
	private List<VehicleMovement> toVehicleMovements;
}
