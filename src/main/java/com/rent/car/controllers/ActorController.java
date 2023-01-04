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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rent.car.models.Actor;
import com.rent.car.services.ActorService;

@Controller
public class ActorController {

	@Autowired
	private ActorService actorService;
	
	@GetMapping("/actors")
	public String getActors(Actor actor, Model model) {
		model.addAttribute("actors", actorService.getActors());
		return "/people/actor";
	}
	
	@RequestMapping("actors/findById")
	@ResponseBody
	public Optional<Actor> findById(int id) {
		return actorService.findById(id);
	}
	
	@PostMapping("/actors/addNew")
	public String addNew(@Valid Actor actor, BindingResult bindingResult, Model model, RedirectAttributes redir) {
		
		if (bindingResult.hasErrors()) {
			return "/register";
		}
		
		actorService.save(actor);
		redir.addFlashAttribute("message", "You successfully registered! You can now login.");
		return "redirect:/login";
	}
}
