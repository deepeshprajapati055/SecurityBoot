package com.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/home")
	public String homePage() {
		return "index";
	}
	@GetMapping("/admin")
	public String adminPage() {
		return "admin";
	}
	@GetMapping("/user")
	public String userPage() {
		return "user";
	}
}
