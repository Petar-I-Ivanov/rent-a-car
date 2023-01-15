package com.rent.car.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleMovement {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="vehicleId", insertable=false, updatable=false)
	private Vehicle vehicle;
	private int vehicleId;
	
	@ManyToOne
	@JoinColumn(name="fromLocationId", insertable=false, updatable=false)
	private Location fromLocation;
	private int fromLocationId;
	
	@FutureOrPresent(message = "Field should be in present or future.")
	@DateTimeFormat(pattern = "yyyy-MM-dd")	
	private Date fromDate;
	
	@ManyToOne
	@JoinColumn(name="toLocationId", insertable=false, updatable=false)
	private Location toLocation;
	private int toLocationId;
	
	@Future(message = "Field should be in future.")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date toDate;

	private String remarks;
}
