package br.com.xavecoding.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HelloController {

	@GetMapping("/hello-servlet")
	public String hello(HttpServletRequest request) {
		request.setAttribute("nome","Samuka");
		
		return "hello";
	}
	
	@GetMapping("/hello-model")
	public String hello(Model model) {
		model.addAttribute("nome","Zezinho");
		
		return "hello";
	}
	
	@GetMapping("/hello")
	public ModelAndView hello() {
		ModelAndView mv = new ModelAndView("hello");
		mv.addObject("nome", "Maria");
		
		
		return mv;
	}
}
