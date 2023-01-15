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
import javax.validation.constraints.Pattern;

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
	
	@FutureOrPresent(message = "Field should be in present or future.")
	@DateTimeFormat(pattern = "yyyy-MM-dd")	
	private Date dateOut;
	
	@Pattern(regexp = "^[0-9]{2}:[0-9]{2}$", message = "Field should be time (like 08:59).")
	private String timeOut;
	
	@Future(message = "Field should be in future.")
	@DateTimeFormat(pattern = "yyyy-MM-dd")	
	private Date dateIn;
	
	@Pattern(regexp = "^[0-9]{2}:[0-9]{2}$", message = "Field should be time (like 08:59).")
	private String timeIn;
	
	@ManyToOne
	@JoinColumn(name="clientId", insertable=false, updatable=false)
	private Client client;
	private int clientId;
	
	@ManyToOne
	@JoinColumn(name="locationId", insertable=false, updatable=false)
	private Location location;
	private int locationId;
	
	@Pattern(regexp = "^[0-9]+.[0-9]{1,2}$", message = "Field must be double number.")
	private String price;
	
	private String remarks;
}
