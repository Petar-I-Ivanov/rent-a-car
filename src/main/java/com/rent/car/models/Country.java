package com.rent.car.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// shows that the class is Entity
@Entity

// lombok generates all the getters and setters
@Data

//lombok generates constructor that doesn't need parameters 
@NoArgsConstructor

//lombok generates constructor that needs all the parameters
@AllArgsConstructor

// used when @OneToMany relationships
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class Country {

//	shows that this is the ID of that entity
	@Id
	
//	auto generates value for id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String code;
	private String capital;
	private String description;
	private String nationality;
	private String continent;
	
//	one country have many states
//	used when the relationship is created
//	mappedBy tells the hyperlink that the connection is done by State
	@OneToMany(mappedBy = "country")
	private List<Client> clients;
	
//	@OneToMany(mappedBy = "country")
//	private List<Employee> employees;
	
	@OneToMany(mappedBy = "country")
	private List<Location> locations;
	
	@OneToMany(mappedBy = "country")
	private List<State> states;
	
	@OneToMany(mappedBy = "country")
	private List<Supplier> suppliers;
}
