package com.rent.car;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
	
	@GetMapping("/logout")
	public String getLogout() {
		return "login";
	}
	
	@GetMapping("/register")
	public String getRegister() {
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
