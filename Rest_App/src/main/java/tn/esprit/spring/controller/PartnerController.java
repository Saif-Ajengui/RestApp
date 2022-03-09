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

import tn.esprit.spring.entity.Partner;
import tn.esprit.spring.service.IPartnerService;


	@RestController
	@RequestMapping(path ="/Partner")
public class PartnerController {

		@Autowired
		IPartnerService iPartnerService;
		
		//CRUD Partner
		
		@PostMapping
	    @RequestMapping(path = "/new/{idc}")
	    @ResponseBody
	    public Partner addPartner(@RequestBody Partner p , @PathVariable int idc) throws Exception{
	        return iPartnerService.addPartner(p,idc);
	    }
		
		@PutMapping
	    @RequestMapping(path = "/edit/{id}")
	    @ResponseBody
	    public Partner updatePartner(@RequestBody Partner p ,@PathVariable int id) throws Exception{
	        return iPartnerService.updatePartner(p,id );
	    }
		
		
		@DeleteMapping({"/delete/{id}"})
	    public String deletePartner(@PathVariable int id) throws Exception{
	        return iPartnerService.deletePartner(id);
	        
	    }
		
		@GetMapping
	    @RequestMapping(path ="/list")
	    public List<Partner> getAllPartner(){
	        return iPartnerService.getAllPartner();
	    }
		
		



}
