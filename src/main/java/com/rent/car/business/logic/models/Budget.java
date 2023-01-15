package com.rent.car.business.logic.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

@Table(uniqueConstraints = {
		@UniqueConstraint(columnNames = {"action", "actionId"})
})
public class Budget {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Positive(message = "Field should be positive.")
	private double price;
	
	@Temporal(TemporalType.DATE)
    private Date date;
    
    @Column(length = 15)
    private String action;
    
    @Column(nullable = false)
    private int actionId;
    
}
