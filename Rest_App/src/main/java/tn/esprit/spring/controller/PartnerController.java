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

import com.example.demo.Partner;

import tn.esprit.spring.Entity.Collaborator;
import tn.esprit.spring.service.IPartnerService;


	@RestController
	@RequestMapping(path ="/api/Partner")
public class PartnerController {

		@Autowired
		IPartnerService iPartnerService;
		
		//CRUD Partner
		
		@PostMapping
	    @RequestMapping(path = "/Partner/new")
	    @ResponseBody
	    public Partner addPartner(@RequestBody Partner p) throws Exception{
	        return iPartnerService.addPartner(p);
	    }
		
		@PutMapping
	    @RequestMapping(path = "/Partner/edit{id)}")
	    @ResponseBody
	    public Partner updatePartner(@RequestBody Partner p ,@PathVariable int id) throws Exception{
	        return iPartnerService.updatePartner(p,id );
	    }
		
		
		@DeleteMapping({"/Partner/delete/{id}"})
	    public void deletePartner(@PathVariable int id) throws Exception{
	        iPartnerService.deletePartner(id);
	    }
		
		@GetMapping
	    @RequestMapping(path ="/partner")
	    public List<Partner> getAllPartner(){
	        return iPartnerService.getAllPartner();
	    }
		
		



}
