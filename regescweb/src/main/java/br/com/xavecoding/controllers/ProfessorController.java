package br.com.xavecoding.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.xavecoding.models.Professor;
import br.com.xavecoding.models.StatusProfessor;
import br.com.xavecoding.regescweb.dto.RequisicaoFormProfessor;
import br.com.xavecoding.repositories.ProfessorRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/professores")
public class ProfessorController {
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	//Action para retornar a listagem do professor
	@GetMapping("")
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
	
	@GetMapping("/new")
	public ModelAndView novo(RequisicaoFormProfessor requisiscao) {
		ModelAndView mv = new ModelAndView("professores/new");
		mv.addObject("listaStatusProfessor", StatusProfessor.values());
				
		return mv;
		
	}
	@PostMapping("")
	public ModelAndView create(@Valid RequisicaoFormProfessor requisiscao, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			System.out.print("TEM ERROS");
			
			ModelAndView mv = new ModelAndView("professores/new");
			//Reenviando a colection de statusprofessor
			mv.addObject("listaStatusProfessor", StatusProfessor.values());
			return  mv;
		}else {
			Professor professor = requisiscao.toProfessor();
			
			this.professorRepository.save(professor);
						
			return new ModelAndView("redirect:/professores/"+professor.getId());		}
		
		
	}
	
	@GetMapping("/{id}")
	public ModelAndView show(@PathVariable Long id) {
		Optional<Professor> prof =  this.professorRepository.findById(id);
		
		if(prof.isPresent()) {
			Professor professor = prof.get();
			ModelAndView mv = new ModelAndView("professores/show");
			mv.addObject("professor",professor);
			mv.addObject("listaStatusProfessor", StatusProfessor.values());
			return mv;
		}else {
			System.out.println("NÃO ACHOU PROFESSOR COM ID: "+id);
			return new ModelAndView("redirect:/professores");
 		}
		
	}
	
	@GetMapping("/{id}/edit")
	public ModelAndView edit(@PathVariable Long id, RequisicaoFormProfessor requisicao) {
		Optional<Professor> optional = this.professorRepository.findById(id);
		
		if(optional.isPresent()) {
			Professor professor = optional.get();
			requisicao.fromProfessor(professor);
			ModelAndView mv = new ModelAndView("professores/edit");
			mv.addObject("professorId", professor.getId());
			mv.addObject("listaStatusProfessor", StatusProfessor.values());
			return mv;
		
		}else {
			System.out.print("NÃO CHOU");
			return new ModelAndView("redirect:/professores");
		}
		
	}
	
	@PostMapping("/{id}")
	public ModelAndView update(@PathVariable Long  id, @Valid RequisicaoFormProfessor requisicao, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			
			ModelAndView mv = new ModelAndView("professores/edit");
			mv.addObject("professorId",id);
			mv.addObject("listaStatusProfessor", StatusProfessor.values());
			
			
			return mv;
		}else {
			Optional<Professor> optional = this.professorRepository.findById(id);
			
			if(optional.isPresent()) {
			
				//aqui o dto para converter
				Professor professor = requisicao.toProfessor(optional.get());
				
				//vai atualizar ao inves de criar um novo
				this.professorRepository.save(professor);
				
				return new ModelAndView("redirect:/professores/"+professor.getId());
				
				
			}else {
				System.out.print("NÃO ENCONTRADO");
							
				return new ModelAndView("redirect:/professores");
			}
		}
		
	}

}
