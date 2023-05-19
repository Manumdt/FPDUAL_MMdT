package com.pokeapi.pokeapi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class demo {

	@GetMapping("/saludar")
	public String saludar() {
		
		return "Hola Mundo desde Spring";
	}
}
