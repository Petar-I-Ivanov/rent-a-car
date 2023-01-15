package com.rent.car.models;

import java.util.Date;

import javax.persistence.Column;
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
public class VehicleMaintenance {
		
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="vehicleId", insertable=false, updatable=false)
	private Vehicle vehicle;
	private int vehicleId;
	
	@FutureOrPresent(message = "Field should be in present or future.")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	
	@Future(message = "Field should be in future.")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;
	
	@Pattern(regexp = "^[0-9]+.[0-9]{1,2}$", message = "Field must be double number.")
	private String price;
	
	@ManyToOne
	@JoinColumn(name="supplierId", insertable=false, updatable=false)
	private Supplier supplier;
	private int supplierId;
	
	private String remarks;
}
