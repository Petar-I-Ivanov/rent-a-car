package com.rent.car.models;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import lombok.EqualsAndHashCode;

//@Data

// tells spring it's not entity and doesn't need table
// it is used as an abstract class and will be inheredit
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
public class CommonObject extends Auditable<String> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "Field cannot be blank.")
	@Size(min = 4, max = 105, message = "Field length should be between 4 and 105.")
	@Pattern(regexp = "^[A-Z][a-z]+(?: [A-Z][a-z]+)*$",
	message = "Field should contains only letters with capitalized first and after space.")
	@Column(nullable = false, unique = true, length = 105)
	private String description;
	private String details;
	
	public CommonObject() {
		
	}

	public CommonObject(Integer id, String description, String details) {
		this.id = id;
		this.description = description;
		this.details = details;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDetails() {
		return details;
	}
	
	public void setDetails(String details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "CommonObject [id=" + id + ", description=" + description + ", details=" + details + "]";
	}
}
