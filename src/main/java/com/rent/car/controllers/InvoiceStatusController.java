package com.rent.car.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rent.car.models.InvoiceStatus;
import com.rent.car.services.InvoiceStatusService;

@Controller
public class InvoiceStatusController {

	@Autowired private InvoiceStatusService invoiceStatusService;

	@PreAuthorize("hasAnyAuthority('Manager', 'Admin', 'Super Admin')")
	@GetMapping("/invoiceStatuses")
	public String getInvoiceStatuses(InvoiceStatus invoiceStatus, Model model) {
		
		model.addAttribute("invoiceStatuses", invoiceStatusService.getInvoiceStatuses());
		return "/types/invoiceStatus";
	}
	
	@PreAuthorize("hasAnyAuthority('Manager', 'Admin', 'Super Admin')")
	@PostMapping("/invoiceStatuses/addNew")
	public String addNew(@Valid InvoiceStatus invoiceStatus, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("invoiceStatuses", invoiceStatusService.getInvoiceStatuses());
			return "/types/invoiceStatus";
		}
		
		invoiceStatusService.save(invoiceStatus);
		return "redirect:/invoiceStatuses";
	}
	
	@PreAuthorize("hasAnyAuthority('Manager', 'Admin', 'Super Admin')")
	@RequestMapping("/invoiceStatuses/findById")
	@ResponseBody
	public Optional<InvoiceStatus> findById(int id) {
		return invoiceStatusService.findById(id);
	}
	
	@PreAuthorize("hasAnyAuthority('Manager', 'Admin', 'Super Admin')")
	@RequestMapping(value="/invoiceStatuses/update", method= {RequestMethod.PUT, RequestMethod.GET})
	public String update(@Valid InvoiceStatus invoiceStatus, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("invoiceStatuses", invoiceStatusService.getInvoiceStatuses());
			return "/types/invoiceStatus";
		}
		
		invoiceStatusService.save(invoiceStatus);
		return "redirect:/invoiceStatuses";
	}
	
	@PreAuthorize("hasAnyAuthority('Admin', 'Super Admin')")
	@RequestMapping(value="/invoiceStatuses/delete", method= {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(int id) {
		invoiceStatusService.delete(id);
		return "redirect:/invoiceStatuses";
	}
}
