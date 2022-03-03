package tn.esprit.spring.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.service.CalendarrService;

@RestController
@RequestMapping("/calendarr")
public class CalendarrControl {
	
	@Autowired
	private CalendarrService calendarrService;
	

	// http://localhost:8082/examen/calendarr/ajouterCenlendar
	    //{"missionId":1,"employeId":2,"dateDebut":"2020-03-01","dateFin":"2021-03-01"}
		
//		@PostMapping("/ajouterTimesheet/idmission/idemp/dated/datef")
	//	@ResponseBody
		//public void ajouterTimesheet(@PathVariable("idmission") int missionId, @PathVariable("idemp") int employeId, @PathVariable("dated") Date dateDebut,@PathVariable("datef") Date dateFin) {
			//calendarrService.ajouterCanlend(missionId, employeId, dateDebut, dateFin);

		//}
	
	
	

	
	/*
	// http://localhost:8082/examen/calendarr/scheduletaskGREEDY_METHOD
		@Transactional
		@PutMapping("/scheduletaskGREEDY_METHOD")
		public void scheduletaskGREEDY_METHOD() {
			calendarrService.schedulingtaskGREEDY_METHOD();
		}
		
		*/

}
