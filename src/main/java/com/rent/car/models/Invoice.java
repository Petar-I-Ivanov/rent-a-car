package com.rent.car.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.FutureOrPresent;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")	
	@FutureOrPresent(message = "Date should NOT be in past.")
	private Date invoiceDate;
	
	@ManyToOne
	@JoinColumn(name="invoiceStatusId", insertable=false, updatable=false)	
	private InvoiceStatus invoiceStatus;
	private int invoiceStatusId;
	
	@ManyToOne
	@JoinColumn(name="clientId", insertable=false, updatable=false)	
	private Client client;
	private int clientId;
	
	private String remarks;	
}
