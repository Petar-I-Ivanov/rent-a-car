package com.rent.car.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rent.car.models.Invoice;
import com.rent.car.models.InvoiceStatus;
import com.rent.car.models.Client;
import com.rent.car.services.InvoiceService;
import com.rent.car.services.InvoiceStatusService;
import com.rent.car.services.ClientService;

@Controller
public class InvoiceController {

	@Autowired private InvoiceService invoiceService;
	@Autowired private InvoiceStatusService invoiceStatusService;
	@Autowired private ClientService clientService;

	@GetMapping("/invoices")
	public String getInvoices(Model model) {
		
		List<Invoice> invoiceList = invoiceService.getInvoices();
		model.addAttribute("invoices", invoiceList);
		
		List<InvoiceStatus> invoiceStatusList = invoiceStatusService.getInvoiceStatuses();
		model.addAttribute("invoiceStatuses", invoiceStatusList);
		
		List<Client> clientList = clientService.getClients();
		model.addAttribute("clients", clientList);
		
		return "/types/invoice";
	}
	
	@PostMapping("/invoices/addNew")
	public String addNew(Invoice invoice) {
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
}
