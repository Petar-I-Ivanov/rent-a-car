package com.rent.car.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.rent.car.models.Actor;

@Controller
public class ApplicationController {
	
	@GetMapping("/index")
	public String getHome() {
		return "index";
	}
	
	@GetMapping("/login")
	public String getLogin() {
		return "login";
	}
	
	@GetMapping("/register")
	public String getRegister(Actor actor) {
		return "register";
	}
	
	@GetMapping("/accessDenied")
	public String getAccessDenied() {
		return "accessDenied";
	}
	
//	@GetMapping("/error")
//	public String getError() {
//		return "error";
//	}
}
