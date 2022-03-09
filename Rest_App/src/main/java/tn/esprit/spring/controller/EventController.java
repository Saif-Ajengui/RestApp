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

import tn.esprit.spring.config.FileUploadUtil;
import tn.esprit.spring.entity.Event;
import tn.esprit.spring.service.EventServiceImp;
import tn.esprit.spring.service.ParticipationServiceImpl;


@RestController
public class EventController {

	@Autowired  
	private EventServiceImp eventServiceImpl;  

	
	@Autowired
	private ParticipationServiceImpl participationServiceImpl;
	
	//creating post mapping that post the event detail in the database  
		ObjectMapper objectMapper = new ObjectMapper();

	
	@PostMapping(value="/event/add-event", consumes = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE
	})  
	public ResponseEntity<String> addEvent(@RequestPart("evJson")String evJson,@RequestPart("image") MultipartFile file) throws IOException   
	{  Event ev  = new Event();
	 
		String fileNameRepo = StringUtils.cleanPath(file.getOriginalFilename());
		String uploadDir = "uploads/";
		System.out.println("image name ="+fileNameRepo);
		try {
			

					
				ev= objectMapper.readValue(evJson, Event.class);
				String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/uploads/")
						.path(fileNameRepo).toUriString();
				System.out.println("file url =====>"+fileDownloadUri);
				
				ev.setImage(fileDownloadUri.getBytes());
				FileUploadUtil.saveFile(uploadDir, fileNameRepo, file);
				
				
			
				eventServiceImpl.addEvent(ev);  

			
			

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		
		
	    return new ResponseEntity<String>("Event added ",HttpStatus.OK);
	}
	
	//creating put mapping that updates the event detail   
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
		
		//METIER
		
		@PutMapping("/event/affecter-category-event/{category}/{idevent}")  
		public String affecterCategoryEvent(@PathVariable("category")String category,@PathVariable("idevent")int idevent)   
		{  
				
					return eventServiceImpl.affecterCategoryEvent(category, idevent);
					
		}
		
		//METIER
		@GetMapping("/event/upcomingEvent")
		public List<Event> upcomingEvents() {
			List<Event> upevents = eventServiceImpl.upcomeEvents();
			System.out.println("hi="+upevents);
			return upevents;
		}
	
		
		//METIER:

		@GetMapping("/event/bestEventsByViews")
		public Map<Integer, Integer> bestEventsByViews(){
			return eventServiceImpl.getEventsByViews();
			}
		//METIER:

		@GetMapping("/event/displaybestEventsByViews")
		public List<String> displaybestEventsByViews(){
			return eventServiceImpl.displayBestEventsByViews();
			}
		
		//METIER
		@DeleteMapping("/event/delete-event/{event-id}")
		@ResponseBody
		public ResponseEntity<String> deleteEvent(@PathVariable("event-id") int eventID) {
			
			eventServiceImpl.refundUsers(eventID);//refund contributions & participations prices to its users

			eventServiceImpl.deleteEvent(eventID);

			return new ResponseEntity<String>("Event canceled with upgrading price and refund money to user participate",HttpStatus.ACCEPTED);
		}
		
		
	}
