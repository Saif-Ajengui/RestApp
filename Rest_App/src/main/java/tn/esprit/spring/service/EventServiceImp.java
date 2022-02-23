package tn.esprit.spring.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
		public Event findEventById(int id) {
			return  iEventRepository.findById(id).get();
		} 

		//creating getAll method that retrieve all events from database
		@Override
		public List<Event> getAllEvents() {
			
			List<Event>events = new ArrayList<Event>();
			iEventRepository.findAll().forEach(e ->events.add(e));
			return events;
		}
		@Override
		public int updateEvent(Event e, int id) {
			 
				return iEventRepository.updateEvent(e.getTitle(),e.getDate() , e.getHour(), 
							e.getAddress(), e.getDescription(), e.getPlacesNbr(),e.getCategory(),e.getTicketPrice(),e.isStatus(),e.getImage(),e.getIdEvenement());
			}
		
}
