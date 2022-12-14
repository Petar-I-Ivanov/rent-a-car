package com.rent.car.models;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleHire {
		
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="vehicleId", insertable=false, updatable=false)
	private Vehicle vehicle;
	private int vehicleId;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")	
	private Date dateOut;
	private String timeOut;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")	
	private Date dateIn;
	private String timeIn;
	
	@ManyToOne
	@JoinColumn(name="clientId", insertable=false, updatable=false)
	private Client client;
	private int clientId;
	
	@ManyToOne
	@JoinColumn(name="locationId", insertable=false, updatable=false)
	private Location location;
	private int locationId;
	
	private String price;
	private String remarks;
}
