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
public class State {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String capital;
	private String code;
	
//	many states are in one country
	@ManyToOne
	
//	connecting country with the countryId
//	you can't insert that column (Country) and can't update it (because they're false)	
	@JoinColumn(name="countryId", insertable=false, updatable=false)
	private Country country;
	private int countryId;
	
	private String details;
	
	@OneToMany(mappedBy = "state")
	private List<Client> clients;
	
//	@OneToMany(mappedBy = "country")
//	private List<Employee> employees;
	
	@OneToMany(mappedBy = "state")
	private List<Location> locations;
	
	@OneToMany(mappedBy = "state")
	private List<Supplier> suppliers;
}
