package com.rent.car.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
	
	@Pattern(regexp = "^[0-9]{4}$", message = "Field should be 4 digits.")
	@Column(nullable = false, unique = true, length = 4)
	private String code;
	
	@Size(min = 4, max = 105, message = "Field size must be between 4 and 105.")
	@Pattern(regexp = "^[A-Z][a-z]+(?: [A-Z][a-z]+)*$",
	message = "Field should contains only letters with capitalized first and after space.")
	@Column(nullable = false, unique = true, length = 105)
	private String capital;
	
	@Size(min = 4, max = 60, message = "Field size must be between 4 and 60.")
	@Pattern(regexp = "^[A-Z][a-z]+(?: [A-Z][a-z]+)*$",
	message = "Field should contains only letters with capitalized first and after space.")
	@Column(nullable = false, unique = true, length = 60)
	private String description;
	
	@Size(min = 4, max = 60, message = "Field size must be between 4 and 60.")
	@Pattern(regexp = "^[A-Z][a-z]+(?: [A-Z][a-z]+)*$",
	message = "Field should contains only letters with capitalized first and after space.")
	@Column(nullable = false, unique = true, length = 60)
	private String nationality;
	
	@NotNull(message = "Field should not be null.")
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 15)
	private ContinentEnum continent;
	
//	one country have many states
//	used when the relationship is created
//	mappedBy tells the hyperlink that the connection is done by Client in parameter "country"
	@OneToMany(mappedBy = "country")
	private List<Client> clients;
	
	@OneToMany(mappedBy = "country")
	private List<Employee> employees;
	
	@OneToMany(mappedBy = "country")
	private List<Location> locations;
	
	@OneToMany(mappedBy = "country")
	private List<State> states;
	
	@OneToMany(mappedBy = "country")
	private List<Supplier> suppliers;
}
