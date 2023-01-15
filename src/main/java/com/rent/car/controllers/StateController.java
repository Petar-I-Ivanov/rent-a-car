package com.rent.car.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rent.car.models.State;
import com.rent.car.services.CountryService;
import com.rent.car.services.StateService;

@Controller
public class StateController {

//	Autowire the service into the controller
	@Autowired private StateService stateService;
	@Autowired private CountryService countryService;
	
	private State lastGivenState;

	@PreAuthorize("hasAnyAuthority('Manager', 'Admin', 'Super Admin')")
	@GetMapping("/states")
	public String getStates(State state, Model model) {
		
		model = setModel(model);
		return "/globals/state";
	}
	
	@PreAuthorize("hasAnyAuthority('Manager', 'Admin', 'Super Admin')")
	@PostMapping("/states/addNew")
	public String addNew(@Valid State state, BindingResult bindingResult, Model model) {
		
		if (state.getId() != 0) {
			bindingResult.addError(new FieldError("state", "id", "Please don't change id."));
		}
		
		if (bindingResult.hasErrors()) {
			model = setModel(model);
			return "/globals/state";
		}
		
		stateService.save(state);
		return "redirect:/states";
	}
	
	@PreAuthorize("hasAnyAuthority('Manager', 'Admin', 'Super Admin')")
	@RequestMapping("/states/findById")
	@ResponseBody
	public Optional<State> findById(int id) {
		
		lastGivenState = stateService.findById(id).orElse(null);
		return stateService.findById(id);
	}
	
	@PreAuthorize("hasAnyAuthority('Manager', 'Admin', 'Super Admin')")
	@RequestMapping(value="/states/update", method= {RequestMethod.PUT, RequestMethod.GET})
	public String update(@Valid State state, BindingResult bindingResult, Model model) {
		
		if (state.getId() != lastGivenState.getId()) {
			bindingResult.addError(new FieldError("state", "id", "Please don't change id."));
		}
		
		if (bindingResult.hasErrors()) {
			model = setModel(model);
			return "/globals/state";
		}
		
		stateService.save(state);
		return "redirect:/states";
	}
	
	@PreAuthorize("hasAnyAuthority('Admin', 'Super Admin')")
	@RequestMapping(value="/states/delete", method= {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(int id) {
		
		stateService.delete(id);
		return "redirect:/states";
	}
	
	private Model setModel(Model model) {
		
		model.addAttribute("states", stateService.getStates());
		model.addAttribute("countries", countryService.getCountries());
		
		return model;
	}
}
