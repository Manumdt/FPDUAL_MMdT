package com.example.PokeApi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
   
	@GetMapping("/")
    public String home() {
        return "index"; 
    }
	
	@GetMapping("/error")
	public String handleError() {
	    return "error"; // nombre de la vista personalizada para mostrar el error
	}
}