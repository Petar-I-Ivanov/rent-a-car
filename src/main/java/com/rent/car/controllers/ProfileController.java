package com.rent.car.controllers;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rent.car.models.Actor;
import com.rent.car.models.Client;
import com.rent.car.models.Employee;
import com.rent.car.security.models.ChangePassword;
import com.rent.car.services.ActorService;
import com.rent.car.services.ClientService;
import com.rent.car.services.CountryService;
import com.rent.car.services.EmployeeService;
import com.rent.car.services.EmployeeTypeService;
import com.rent.car.services.JobTitleService;
import com.rent.car.services.StateService;

@Controller
public class ProfileController {

	@Autowired private ActorService actorService;
	@Autowired private ClientService clientService;
	@Autowired private EmployeeService employeeService;
	
	@Autowired private CountryService countryService;
	@Autowired private StateService stateService;
	
	@Autowired private JobTitleService jobTitleService;
	@Autowired private EmployeeTypeService employeeTypeService;
	
	@GetMapping("/profile")
	public String profile(Model model, Principal principal) {
		
		Actor actor = actorService.findByUsername(principal.getName());
		
		if (actor.getEmployee() != null) {
			model.addAttribute("employee", actor.getEmployee());
		}
		
		if (actor.getClient() != null) {
			model.addAttribute("client", actor.getClient());
		}
		
		model = setModel(model);
		return "profile/profile";
	}
	
	@RequestMapping(value="/clientProfileUpdate", method= {RequestMethod.PUT, RequestMethod.GET})
	public String clientProfileUpdate(@Valid Client client, BindingResult bindingResult,
										Principal principal, Model model) {
		
		Actor actor = actorService.findByUsername(principal.getName());
		
		if (bindingResult.hasErrors()) {
			model = setModel(model);
			model.addAttribute("client", actor.getClient());
			return "profile/profile";
		}
		
		client.setId(actor.getClient().getActorId());
		client.setActorId(actor.getId());
		clientService.save(client);
		
		return "redirect:/profile";
	}
	
	@RequestMapping(value="/employeeProfileUpdate", method= {RequestMethod.PUT, RequestMethod.GET})
	public String employeeProfileUpdate(@Valid Employee employee, BindingResult bindingResult,
										Principal principal, Model model) {
		
		Actor actor = actorService.findByUsername(principal.getName());
		
		if (bindingResult.hasErrors()) {
			model = setModel(model);
			model.addAttribute("client", actor.getClient());
			model.addAttribute("employee", actor.getEmployee());
			return "profile/profile";
		}
		
		employee.setId(actor.getEmployee().getActorId());
		employee.setActorId(actor.getId());
		employee.setUsername(actor.getUsername());
		employeeService.save(employee);
		
		return "redirect:/profile";
	}
	
	@RequestMapping(value="/changePassword", method= {RequestMethod.PUT, RequestMethod.GET})
	public String changePassword(@Valid ChangePassword changePassword,
							BindingResult bindingResult, Model model) {
		
		if (!changePassword.getNewPassword().equals(changePassword.getConfirmNewPassword())) {
			bindingResult.addError(new FieldError("changePassword", "newPassword", "New password doesn't match."));
		}
		
		if (bindingResult.hasErrors()) {
			model = setModel(model);
			return "profile/profile";
		}
		
		return "redirect:/profile";
	}
	
	private Model setModel(Model model) {
		
		model.addAttribute("countries", countryService.getCountries());
		model.addAttribute("states", stateService.getStates());
		
		model.addAttribute("jobTitles", jobTitleService.getJobTitles());
		model.addAttribute("employeeTypes", employeeTypeService.getEmployeeTypes());
		
		model.addAttribute("changePassword", new ChangePassword());
		
		return model;
	}
}
