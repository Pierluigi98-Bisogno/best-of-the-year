package com.example.bestoftheyear.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String home(Model model) {
		// Sostituisci "Il Tuo Nome" con il tuo nome reale
		model.addAttribute("name", "Pierluigi");
		return "index";
	}
}