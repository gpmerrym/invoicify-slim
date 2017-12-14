package com.lmig.gfc.invoicify.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lmig.gfc.invoicify.models.BillingRecord;
import com.lmig.gfc.invoicify.models.Company;
import com.lmig.gfc.invoicify.models.Invoice;
import com.lmig.gfc.invoicify.models.InvoiceLineItem;
import com.lmig.gfc.invoicify.models.User;
import com.lmig.gfc.invoicify.services.BillingRepository;
import com.lmig.gfc.invoicify.services.CompanyRepository;
import com.lmig.gfc.invoicify.services.InvoiceLineItemRepository;
import com.lmig.gfc.invoicify.services.InvoiceRepository;

@Controller
@RequestMapping("/invoices")
public class InvoicesController {

	private InvoiceRepository invoiceRepo;
	private CompanyRepository companyRepo;
	private BillingRepository billingRepo;

	public InvoicesController(InvoiceRepository invoiceRepo, InvoiceLineItemRepository invoiceLineItemRepo,
			CompanyRepository companyRepo, BillingRepository billingRepo) {

		this.invoiceRepo = invoiceRepo;
		this.companyRepo = companyRepo;
		this.billingRepo = billingRepo;
	}

	@GetMapping("")
	public ModelAndView showInvoices() {
		ModelAndView mv = new ModelAndView("invoices/list");

		List<Invoice> invoices = invoiceRepo.findAll();

		mv.addObject("invoices", invoices);
		mv.addObject("showTable", invoices.size() >= 1);

		// Get all the invoices and add them to the model and view with the key
		// "invoices"
		// Add a key to the model and view named "showTable" which should be true if
		// there's more than one invoice and false if there are zero invoices

		return mv;
	}

	@GetMapping("/clients")
	public ModelAndView chooseClient() {
		ModelAndView mv = new ModelAndView("invoices/clients");
		List<Company> clients = companyRepo.findAll();

		mv.addObject("clients", clients);
		
		// Get all the clients and add them to the model and view with the key "clients"

		return mv;
	}

	@GetMapping("/clients/{clientId}")
	public ModelAndView createInvoice(@PathVariable Long clientId) {
		ModelAndView mv = new ModelAndView("invoices/billing-records-list");
		
		List<BillingRecord> records = billingRepo.findByClientIdAndLineItemIsNull(clientId);
		mv.addObject("records", records);
		mv.addObject("clientId", clientId);

		
		
		//mv.addObject("records", records);

		// Get all the billing records for the specified client that have no associated invoice line item and add them with the key "records"
		// Add the client id to the model and view with the key "clientId"

		return mv;
	}

	@PostMapping("/clients/{clientId}")
	public String createInvoice(Invoice invoice, @PathVariable Long clientId, long[] recordIds, Authentication auth) {
		
		User user = (User) auth.getPrincipal();
		// Get the user from the auth.getPrincipal() method
		ArrayList<BillingRecord> billingRecords = new ArrayList<BillingRecord>();
		for(long recordId: recordIds) {
			billingRecords.add(billingRepo.findOne(recordId));
		}
		// Find all billing records in the recordIds array
		
		List<InvoiceLineItem> invoicelineItems = new ArrayList<InvoiceLineItem>();
		// Create a new list that can hold invoice line items
		for(BillingRecord billingRecord : billingRecords) {
			InvoiceLineItem invoiceLineItem = new InvoiceLineItem();
			invoiceLineItem.setBillingRecord(billingRecord);
			invoiceLineItem.setCreatedBy(user);
			invoiceLineItem.setInvoice(invoice);
			invoicelineItems.add(invoiceLineItem);
		}
		// For each billing record in the records found from recordIds
			// Create a new invoice line item
			// Set the billing record on the invoice line item
			// Set the created by to the user
			// Set the invoice on the invoice line item
			// Add the invoice line item to the list of invoice line items

		// Set the list of line items on the invoice
		invoice.setInvoice(invoicelineItems);
		// Set the created by on the invoice to the user
		invoice.setCreatedBy(user);
		// Set the client on the invoice to the company identified by clientId
		invoice.setCompany(companyRepo.findOne(clientId));
		// Save the invoice to the database
		invoiceRepo.save(invoice);

		return "redirect:/invoices";
	}

}
