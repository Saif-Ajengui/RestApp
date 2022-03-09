package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Commentaire;
import tn.esprit.spring.entity.Departement;
import tn.esprit.spring.entity.Publication;
import tn.esprit.spring.service.DepartementServiceImpl;


@RestController
public class DepartementController {
	
	@Autowired  
	DepartementServiceImpl DepartementServiceImpl;
	
	@PostMapping("/Departement/add-Departement")  
	public String addDepartement(@RequestBody Departement Departements) throws Exception   
	{  
		return  (DepartementServiceImpl.addDepartement(Departements));  
	} 
	
	@PutMapping("/Departement/update-Departement/{id}")  
	public String updateDepartement(@RequestBody Departement Departements, @PathVariable("id")long id) throws Exception   
	{  
		return  DepartementServiceImpl.updateDepartement(Departements,id);  			  
	}
	@DeleteMapping("/Departement/delete-Departement/{id}")  
	public String deleteDepartement(@PathVariable("id") long id) throws Exception   
	{  
		return (DepartementServiceImpl.deleteDepartement(id));  
	} 		
	
	@GetMapping("/Departement/get-all-Departement")  
	public List<Departement> getAllDepartements()   
	{  
		return DepartementServiceImpl.getAllDepartements();  
	}  
	
	
}
