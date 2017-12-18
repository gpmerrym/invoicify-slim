package com.lmig.gfc.invoicify.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

// This needs to be an entity
@Entity
public class Company {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	// This needs an id
	
	private String name;
	// This needs a name
	
	@OneToMany(mappedBy = "company")
	@JsonIgnore
	private List<Invoice> invoices;
	// This needs a list of invoice objects named invoices as one-to-many relationship mapped by "company"
	
	public Company() {
		invoices = new ArrayList<Invoice>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}
	
	// Lots of getters and setters
	
}
