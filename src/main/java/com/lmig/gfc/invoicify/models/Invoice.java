package com.lmig.gfc.invoicify.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

// This needs to be an entity
@Entity
public class Invoice {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	// This needs an id
	@ManyToOne
	private Company company;
	// This needs a many-to-one relationship to a company named "company"

	@ManyToOne
	private User createdBy;
	// This needs a many-to-one relationship to a user named "createdBy"

	private String invoiceNumber;
	// This needs a string named "invoiceNumber"

	@OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
	private List<InvoiceLineItem> invoice;
	// This needs a one-to-many relationship for a list of invoice line items mapped
	// by "invoice" with a cascade type of ALL

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public List<InvoiceLineItem> getInvoice() {
		return invoice;
	}

	public void setInvoice(List<InvoiceLineItem> invoice) {
		this.invoice = invoice;
	}

	// This needs getters and setters

}
