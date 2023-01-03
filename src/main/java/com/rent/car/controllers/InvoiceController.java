package com.rent.car.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rent.car.models.Invoice;
import com.rent.car.services.InvoiceService;
import com.rent.car.services.InvoiceStatusService;
import com.rent.car.services.ClientService;

@Controller
public class InvoiceController {

	@Autowired private InvoiceService invoiceService;
	@Autowired private InvoiceStatusService invoiceStatusService;
	@Autowired private ClientService clientService;

	@GetMapping("/invoices")
	public String getInvoices(Invoice invoice, Model model) {
		model = setModel(model);
		return "/types/invoice";
	}
	
	@PostMapping("/invoices/addNew")
	public String addNew(@Valid Invoice invoice, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			model = setModel(model);
			return "/type/invoice";
		}
		
		invoiceService.save(invoice);
		return "redirect:/invoices";
	}
	
	@RequestMapping("/invoices/findById")
	@ResponseBody
	public Optional<Invoice> findById(int id) {
		return invoiceService.findById(id);
	}
	
	@RequestMapping(value="/invoices/update", method= {RequestMethod.PUT, RequestMethod.GET})
	public String update(Invoice invoice) {
		invoiceService.save(invoice);
		return "redirect:/invoices";
	}
	
	@RequestMapping(value="/invoices/delete", method= {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(int id) {
		invoiceService.delete(id);
		return "redirect:/invoices";
	}
	
	private Model setModel(Model model) {
		
		model.addAttribute("invoices", invoiceService.getInvoices());
		model.addAttribute("invoiceStatuses", invoiceStatusService.getInvoiceStatuses());
		model.addAttribute("clients", clientService.getClients());
		
		return model;
	}
}
