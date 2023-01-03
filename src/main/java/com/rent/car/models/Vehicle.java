package com.rent.car.models;

import java.util.Date;
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
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

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
public class Vehicle {
		
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank(message = "Field cannot be blank.")
	@Size(min = 4, max = 105, message = "Field length should be between 4 and 105.")
	@Column(nullable = false, unique = true, length = 105)
	private String name;
	
	@ManyToOne
	@JoinColumn(name="vehicleTypeId", insertable=false, updatable=false)
	private VehicleType vehicleType;
	private int vehicleTypeId;	
	
	@Size(max = 10, message = "Field length should be max 10 characters.")
	@Pattern(regexp = "^$|(^[A-Z0-9]+$)", message = "Field contains only upper case and digits.")
	@Column(unique = true, length = 10)
	private String vehicleNumber;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date registrationDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date acquisitionDate;
	
	private String description;
	
	@ManyToOne
	@JoinColumn(name="vehicleMakeId", insertable=false, updatable=false)
	private VehicleMake vehicleMake;
	private int vehicleMakeId;
	
	@Pattern(regexp = "^$|(^[0-9]+\\.[0-9]{2}$)", message = "Field contains only float digit.")
	private String power;
	
	@Pattern(regexp = "^$|(^[0-9]+\\.[0-9]{2}$)", message = "Field contains only float digit.")
	private String fuelCapacity;
	
	@ManyToOne
	@JoinColumn(name="vehicleStatusId", insertable=false, updatable=false)
	private VehicleStatus vehicleStatus;
	private int vehicleStatusId;	
	
	@Pattern(regexp = "^$|(^[0-9]+\\.[0-9]{2}$)", message = "Field contains only float digit.")
	private String netWeight;

	@ManyToOne
	@JoinColumn(name="employeeId", insertable=false, updatable=false)
	private Employee inCharge;
	private int employeeId;
	
	@ManyToOne
	@JoinColumn(name="vehicleModelId", insertable=false, updatable=false)
	private VehicleModel vehicleModel;	
	private int vehicleModelId;

	@ManyToOne
	@JoinColumn(name="locationId", insertable=false, updatable=false)	
	private Location currentLocation;
	private int locationId;
	
	private String remarks;	
	
	@OneToMany(mappedBy = "vehicle")
	private List<VehicleHire> vehicleHire;
	
	@OneToMany(mappedBy = "vehicle")
	private List<VehicleMaintenance> vehicleMaintenance;
	
	@OneToMany(mappedBy = "vehicle")
	private List<VehicleMovement> vehicleMovement;
}
