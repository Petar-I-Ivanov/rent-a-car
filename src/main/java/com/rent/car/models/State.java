package com.rent.car.models;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
	
	@NotBlank(message = "Field cannot be blank.")
	@Size(min = 4, max = 105, message = "Field length should be be between 4 and 105.")
	@Pattern(regexp = "^[A-Z][a-z]+(?: [A-Z][a-z]+)*$",
	message = "Field should contains only letters with capitalized first and after space.")
	@Column(nullable = false, unique = true, length = 105)
	private String name;
	
	@NotBlank(message = "Field cannot be blank.")
	@Size(min = 4, max = 105, message = "Field length should be be between 4 and 105.")
	@Pattern(regexp = "^[A-Z][a-z]+(?: [A-Z][a-z]+)*$",
	message = "Field should contains only letters with capitalized first and after space.")
	@Column(nullable = false, unique = true, length = 105)
	private String capital;
	
	@NotNull(message = "Field cannot be null.")
	@Pattern(regexp = "^[0-9]{4}$", message = "Field should be 4 digits.")
	@Column(nullable = false, unique = true, length = 4)
	private String code;
	
//	many states are in one country
	@ManyToOne
	
//	connecting country with the countryId
	@NotNull(message = "Field cannot be null.")
	@JoinColumn(name="countryId", insertable=false, nullable=false, updatable=false)
	private Country country;
	private int countryId;
	
	private String details;
	
	@OneToMany(mappedBy = "state")
	private List<Client> clients;
	
	@OneToMany(mappedBy = "state")
	private List<Employee> employees;
	
	@OneToMany(mappedBy = "state")
	private List<Location> locations;
	
	@OneToMany(mappedBy = "state")
	private List<Supplier> suppliers;

}
