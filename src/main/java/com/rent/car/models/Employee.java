package com.rent.car.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Employee extends Person {

	@ManyToOne
	@JoinColumn(name="employeeTypeId", insertable=false, updatable=false)
	private EmployeeType employeeType;
	private int employeeTypeId;
	
	private String photo;
	
	@NotBlank(message = "Field cannot be blank.")
	@Size(min = 4, max = 105, message = "Field length should be between 4 and 105.")
	@Column(unique = true, nullable = false, length = 105)
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
