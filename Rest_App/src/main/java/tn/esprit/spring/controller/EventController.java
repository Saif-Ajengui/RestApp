package tn.esprit.spring.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.xml.ws.Response;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import tn.esprit.spring.entity.Event;
import tn.esprit.spring.service.EventServiceImp;
import tn.esprit.spring.service.ParticipationServiceImp;


@RestController
public class EventController {

	@Autowired  
	private EventServiceImp eventServiceImpl;  

	
	@Autowired
	private ParticipationServiceImp participationServiceImpl;
	
	//creating put mapping that updates the event detail   
			@PutMapping("/event/add-event/")  
			public ResponseEntity<String> addEvent(@RequestBody Event events) 
			{  
			
				if(events.getTitle().isEmpty()) {
				    return new ResponseEntity<String>("Title event required",HttpStatus.OK);
				}

				if(events.getDescription().isEmpty()) {
				    return new ResponseEntity<String>("Description event required",HttpStatus.OK);
				}

				if(events.getAddress().isEmpty()) {
				    return new ResponseEntity<String>("Address event required",HttpStatus.OK);
				}
				else {
					eventServiceImpl.addEvent(events);  
				    return new ResponseEntity<String>("Event Added successfully",HttpStatus.OK);
		
				}
						}
	
	@GetMapping("/event/get-all-events")  
	public List<Event> getAllEvents()   
	{  
		return eventServiceImpl.getAllEvents();  
	}  
	
	
	//creating a get mapping that retrieves the detail of a specific event  
	@GetMapping("/event/detail-event/{eventid}")  
	public Event getEvent(@PathVariable("eventid") int eventid)   
	{  
		return eventServiceImpl.findEventById(eventid);  
	}

	
	//creating put mapping that updates the event detail   
		@PutMapping("/event/update-event/{eventid}")  
		public ResponseEntity<String> updateEvent(@RequestBody Event events, @PathVariable("eventid")int eventid)   
		{  
		
			eventServiceImpl.updateEvent(events,eventid);  
		    return new ResponseEntity<String>("Event updated successfully",HttpStatus.OK);
		}
		
		@PutMapping("/event/affecter-event/{iduser}/{idevent}")  
	public  String participateToEvent(@PathVariable("iduser")int iduser,@PathVariable("idevent")int idevent)   
		{  
		
			String result = participationServiceImpl.addParticipation(iduser, idevent);
		    return result;
			
		}
	
	}
