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

import com.rent.car.models.Contact;
import com.rent.car.services.ContactService;

@Controller
public class ContactController {

	@Autowired
	private ContactService contactService;

	@GetMapping("/contacts")
	public String getCountries(Contact contact, Model model) {
		model.addAttribute("contacts", contactService.getContacts());
		return "/people/contact";
	}
	
	@PostMapping("/contacts/addNew")
	public String addNew(@Valid Contact contact, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("contacts", contactService.getContacts());
			return "/people/contact";
		}
		
		contactService.save(contact);
		return "redirect:/contacts";
	}
	
	@RequestMapping("contacts/findById")
	@ResponseBody
	public Optional<Contact> findById(int id) {
		return contactService.findById(id);
	}
	
	@RequestMapping(value="/contacts/update", method= {RequestMethod.PUT, RequestMethod.GET})
	public String update(Contact contact) {
		contactService.save(contact);
		return "redirect:/contacts";
	}
	
	@RequestMapping(value="/contacts/delete", method= {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(int id) {
		contactService.delete(id);
		return "redirect:/contacts";
	}
}
