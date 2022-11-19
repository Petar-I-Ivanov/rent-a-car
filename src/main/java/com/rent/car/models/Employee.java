package com.rent.car.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Employee extends Person {

	@ManyToOne
	@JoinColumn(name="employeeTypeId", insertable=false, updatable=false)
	private EmployeeType employeeType;
	private int employeeTypeId;
	
	private String photo;
	private String username;
	
	@ManyToOne
	@JoinColumn(name="jobTitleId", insertable=false, updatable=false)
	private JobTitle jobTitle;
	private int jobTitleId;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")	
	private Date hireDate;
	
	@OneToMany(mappedBy = "inCharge")
	private List<Vehicle> vehicles;
}
