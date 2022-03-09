package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Donation;
import tn.esprit.spring.service.IDonationService;
import tn.esprit.spring.service.IEventService;
import tn.esprit.spring.service.IPaymentService;



@RestController
public class DonationController {
	
	
	@Autowired
	IDonationService iDonationService;
	
	@Autowired
	IEventService iEventService;

	
	//creating put mapping that updates the event detail   
	//creating post mapping  
		@PostMapping("/donation/event/donation-event/{eventid}/{uid}/{amount}")  
		private ResponseEntity<String> DonationOfEvent(@PathVariable("eventid")int eventid,
				 @PathVariable("uid")int uid,@PathVariable("amount")float amount )   
		{  
			String result = iDonationService.Donation(eventid,uid,amount);  
			
			
			
		    return new ResponseEntity<String>(result,HttpStatus.OK);
		}
	
	
	@GetMapping("/donation/event/displayDonationsEvent/{idevent}")
	public List<Donation>getDonationEvention(@PathVariable("idevent")int idevent) {
		
		return iDonationService.getDonationEvention(iEventService.findEventById(idevent));
	}
	
}
