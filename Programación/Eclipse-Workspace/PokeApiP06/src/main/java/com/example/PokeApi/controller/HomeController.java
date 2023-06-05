package com.example.PokeApi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Clase encargada de asignar index como página home
 * @author Manuel Mateos de Torres
 *
 */
@Controller
public class HomeController {
   
	/**
	 * Método get para establecer el home
	 * @return
	 */
	@GetMapping("/")
    public String home() {
        return "index"; 
    }
	
	/**
	 * Método get para derivar en error
	 * @return
	 */
	@GetMapping("/error")
	public String handleError() {
	    return "error"; // nombre de la vista personalizada para mostrar el error
	}
}