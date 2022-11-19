package com.rent.car.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.rent.car.models.Actor;
import com.rent.car.services.ActorService;

@Controller
public class ActorController {

	@Autowired
	private ActorService actorService;
	
	@GetMapping("/actors")
	public String getActors(Model model) {
		model.addAttribute("actors", actorService.getActors());
		return "/people/actor";
	}
	
	@RequestMapping("actors/findById")
	@ResponseBody
	public Optional<Actor> findById(int id) {
		return actorService.findById(id);
	}
	
	@PostMapping("/actor/addNew")
	public RedirectView addNew(Actor actor, RedirectAttributes redir) {
		
		actorService.save(actor);
		RedirectView redirectView = new RedirectView("/login", true);
		redir.addFlashAttribute("message", "You successfully registered! You can now login.");
		return redirectView;
	}
}
