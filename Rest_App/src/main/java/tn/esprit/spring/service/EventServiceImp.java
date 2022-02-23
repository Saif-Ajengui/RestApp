package tn.esprit.spring.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Event;
import tn.esprit.spring.repository.EventRepository;
import tn.esprit.spring.repository.ParticipationRepository;

@Service
public class EventServiceImp implements IEventService{
	@Autowired 
	private EventRepository iEventRepository;
	@Autowired
	private ParticipationRepository iParticipantRepository;
	
	
	/**********************************Admin**********************************/

	//creating add method that insert  event into database
	@Override
	
	public void addEvent(Event e) {
		
		
	
		iEventRepository.save(e);
	
	
		
		
	}
	//creating deleting method that remove   event by id  from database
		@Override
		public void deleteEvent(int id) {
			Event e = iEventRepository.findById(id).get();
			iEventRepository.delete(e);
			
		}

		@Override
		public void findEventById(int id) {
			 iEventRepository.findById(id).get();
		} 


	
}
