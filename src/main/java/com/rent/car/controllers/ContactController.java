package com.rent.car.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rent.car.models.Contact;
import com.rent.car.services.ContactService;

@Controller
public class ContactController {

	@Autowired
	private ContactService contactService;

	@PreAuthorize("hasAnyAuthority('Human Resource', 'Manager', 'Admin', 'Super Admin')")
	@GetMapping("/contacts")
	public String getCountries(@Param("keyword") String keyword, Contact contact, Model model) {
		model.addAttribute("contacts", contactService.getContacts(keyword));
		return "/people/contact";
	}
	
	@PreAuthorize("hasAnyAuthority('Human Resource', 'Manager', 'Admin', 'Super Admin')")
	@PostMapping("/contacts/addNew")
	public String addNew(@Valid Contact contact, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("contacts", contactService.getContacts());
			return "/people/contact";
		}
		
		contactService.save(contact);
		return "redirect:/contacts";
	}
	
	@PreAuthorize("hasAnyAuthority('Human Resource', 'Manager', 'Admin', 'Super Admin')")
	@RequestMapping("contacts/findById")
	@ResponseBody
	public Optional<Contact> findById(int id) {
		return contactService.findById(id);
	}
	
	@PreAuthorize("hasAnyAuthority('Human Resource', 'Manager', 'Admin', 'Super Admin')")
	@RequestMapping(value="/contacts/update", method= {RequestMethod.PUT, RequestMethod.GET})
	public String update(Contact contact) {
		contactService.save(contact);
		return "redirect:/contacts";
	}
	
	@PreAuthorize("hasAnyAuthority('Admin', 'Super Admin')")
	@RequestMapping(value="/contacts/delete", method= {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(int id) {
		contactService.delete(id);
		return "redirect:/contacts";
	}
}
