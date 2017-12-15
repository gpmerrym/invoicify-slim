package com.lmig.gfc.invoicify.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import com.lmig.gfc.invoicify.models.Company;
import com.lmig.gfc.invoicify.models.FlatFeeBillingRecord;
import com.lmig.gfc.invoicify.models.User;
import com.lmig.gfc.invoicify.services.BillingRepository;
import com.lmig.gfc.invoicify.services.CompanyRepository;

@Controller
@RequestMapping("/billing-records/flat-fees")
public class FlatFeeBillingRecordController {

	private CompanyRepository companyRepo;
	private BillingRepository billingRepo;

	public FlatFeeBillingRecordController(CompanyRepository companyRepo, BillingRepository billingRepo) {
		
		this.companyRepo = companyRepo;
		this.billingRepo = billingRepo;
	}

	@PostMapping("")
	public ModelAndView create(FlatFeeBillingRecord record, long clientId, Authentication auth) {
		
		User user = (User) auth.getPrincipal();
		Company client = companyRepo.findOne(clientId);

		record.setClient(client);
		record.setCreatedBy(user);
		billingRepo.save(record);

		// Get the user from the auth.getPrincipal() method
		// Find the client using the client id
		// Set the client on the record
		// Set the user on the record for the created by property
		// Save the record

		return new ModelAndView("redirect:/billing-records");
	}

}
