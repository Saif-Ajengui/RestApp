package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Contrat;
import tn.esprit.spring.service.IContratService;

@RestController
@RequestMapping(path ="/Contrat")

public class ContratController {

	@Autowired
	IContratService iContratService;
	
	//CRUD Partner
	
	@PostMapping
    @RequestMapping(path = "/new")
    @ResponseBody
    public Contrat addContrat(@RequestBody Contrat c) throws Exception {
		return iContratService.addContrat(c);
	}
	
	@PutMapping
	@RequestMapping(path = "/edit/{id}")
	@ResponseBody
	public Contrat updateContrat(@RequestBody Contrat c,@PathVariable int id) throws Exception {
		return iContratService.updateContrat(c, id);
	}

	@DeleteMapping({"/delete/{id}"})
	public String deleteContrat(@PathVariable int id) throws Exception{
		
		return iContratService.deleteContrat(id);
	}
	

	@GetMapping
    @RequestMapping(path ="/list")
	public List<Contrat> getAllContrat() {
		return iContratService.getAllContrat();
	}
	
	
	@GetMapping
    @RequestMapping(path ="/ContratElevee")
	public List<Contrat> getContratElvee() {
		return iContratService.getContratElvee();
	}
}

