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

import com.rent.car.models.Client;
import com.rent.car.services.CountryService;
import com.rent.car.services.ClientService;
import com.rent.car.services.StateService;

@Controller
public class ClientController {

	@Autowired private ClientService clientService;
	@Autowired private StateService stateService;
	@Autowired private CountryService countryService;

	@PreAuthorize("hasAnyAuthority('Human Resource', 'Manager', 'Admin', 'Super Admin')")
	@GetMapping("/clients")
	public String getClients(Client client, Model model) {
		
		model = setModel(model);
		return "/people/client";
	}
	
	@PreAuthorize("hasAnyAuthority('Human Resource', 'Manager', 'Admin', 'Super Admin')")
	@PostMapping("/clients/addNew")
	public String addNew(@Valid Client client, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			model = setModel(model);
			return "/people/client";
		}
		
		clientService.save(client);
		return "redirect:/clients";
	}
	
	@PreAuthorize("hasAnyAuthority('Human Resource', 'Manager', 'Admin', 'Super Admin')")
	@RequestMapping("/clients/findById")
	@ResponseBody
	public Optional<Client> findById(int id) {
		return clientService.findById(id);
	}
	
	@PreAuthorize("hasAnyAuthority('Human Resource', 'Manager', 'Admin', 'Super Admin')")
	@RequestMapping(value="/clients/update", method= {RequestMethod.PUT, RequestMethod.GET})
	public String update(@Valid Client client, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			model = setModel(model);
			return "/people/client";
		}
		
		clientService.save(client);
		return "redirect:/clients";
	}
	
	@PreAuthorize("hasAnyAuthority('Admin', 'Super Admin')")
	@RequestMapping(value="/clients/delete", method= {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(int id) {
		clientService.delete(id);
		return "redirect:/clients";
	}
	
	private Model setModel(Model model) {
		
		model.addAttribute("clients", clientService.getClients());
		model.addAttribute("states", stateService.getStates());
		model.addAttribute("countries", countryService.getCountries());
		
		return model;
	}
}
