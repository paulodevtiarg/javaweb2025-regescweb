package br.com.xavecoding.controllers;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.servlet.ModelAndView;

import br.com.xavecoding.models.Professor;
import br.com.xavecoding.models.StatusProfessor;
import br.com.xavecoding.regescweb.dto.RequisicaoNovoProfessor;
import br.com.xavecoding.repositories.ProfessorRepository;
import ch.qos.logback.core.net.SyslogOutputStream;
import ch.qos.logback.core.util.StatusPrinter;
import jakarta.validation.Valid;

@Controller
public class ProfessorController {
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	//Action para retornar a listagem do professor
	@GetMapping("/professores")
	public ModelAndView index() {
		
		//Adiciona a lista
		List<Professor> professores = this.professorRepository.findAll();
		//Cira o objeto do modelo e encaminha para determinada view
		ModelAndView mv = new ModelAndView("professores/index");
		
		//Adiciona a lista ao objeto
		mv.addObject("professores", professores);
		
		//retorna o objeto
		return mv;
		
	}
	
	@GetMapping("/professores/new")
	public ModelAndView novo(RequisicaoNovoProfessor requisiscao) {
		ModelAndView mv = new ModelAndView("professores/new");
		mv.addObject("listaStatusProfessor", StatusProfessor.values());
				
		return mv;
		
	}
	@PostMapping("/professores")
	public ModelAndView create(@Valid RequisicaoNovoProfessor requisiscao, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			System.out.print("TEM ERROS");
			
			ModelAndView mv = new ModelAndView("professores/new");
			//Reenviando a colection de statusprofessor
			mv.addObject("listaStatusProfessor", StatusProfessor.values());
			return  mv;
		}else {
			Professor professor = requisiscao.toProfessor();
			
			this.professorRepository.save(professor);
						
			return new ModelAndView("redirect:/professores");
		}
		
		
	}

}
