package com.lmig.gfc.invoicify.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lmig.gfc.invoicify.models.BillingRecord;
import com.lmig.gfc.invoicify.models.Company;
import com.lmig.gfc.invoicify.services.BillingRepository;
import com.lmig.gfc.invoicify.services.CompanyRepository;

@Controller
@RequestMapping("/billing-records")
public class BillingRecordController {
	
	private BillingRepository billingRepo;
	private CompanyRepository companyRepo;
	
	public BillingRecordController(BillingRepository billingRepo, CompanyRepository companyRepo) {
		this.billingRepo = billingRepo;
		this.companyRepo = companyRepo;
	}

	@GetMapping("")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView("billing-records/list");
		
		List<BillingRecord> records = billingRepo.findAll();
		List<Company> companies = companyRepo.findAll();
		
		mv.addObject("records", records);
		mv.addObject("companies", companies);
		
		// Get all the billing records and add them to the model and view with the key "records"
		// Get all the companies and add them to the model and view with the key "companies"
		
		return mv;
	}

}
