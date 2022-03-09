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

import tn.esprit.spring.entity.Offre;
import tn.esprit.spring.service.IOffreService;


@RestController
@RequestMapping(path ="/Offre")
public class OffreController {

	@Autowired
	IOffreService iOffreService;
	
	//CRUD Partner
	
			@PostMapping(path = "/new")
		    
		    public Offre addOffre(@RequestBody Offre o) throws Exception {
				return iOffreService.addOffre(o);
				
			}
			
			@PutMapping
		    @RequestMapping(path = "/edit/{id}")
		    @ResponseBody
		    public Offre updateOffre(@RequestBody Offre o,@PathVariable int id) throws Exception {
				return iOffreService.updateOffre(o, id);
			}
			
			@DeleteMapping({"/delete/{id}"})
			public String deleteOffre(@PathVariable int id) throws Exception {
				return iOffreService.deleteOffre(id);
			}
			
		
			@GetMapping
		    @RequestMapping(path ="/list")
			public List<Offre> getAllOffre(){
				return iOffreService.getAllOffre();
			}
			
			@GetMapping
			@RequestMapping(path ="/OffreByPartner/{id}") 
			public List<Offre> getOffreByPartnerid(@PathVariable ("id") int id) {
				
				return iOffreService.getOffreByPartnerid(id);
			}
			
			@GetMapping
			@RequestMapping(path ="/CountOffreByPartner/{id}")
			public int CountOffreByPartner (@PathVariable ("id") int id) {
				return iOffreService.CountOffreByPartner(id);
			}
}
