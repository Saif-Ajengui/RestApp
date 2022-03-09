package tn.esprit.spring.service;

import java.util.List;
import java.util.Map;

import tn.esprit.spring.entity.Event;

public interface IEventService {
	
	public void addEvent(Event e);
	public void deleteEvent(int id);
	public Event findEventById(int id);
	public int updateEvent(Event e, int id) ;
	public List<Event>getAllEvents();
	
	public String affecterCategoryEvent(String category, int idevent);
	
	public List<Event> upcomeEvents();
	
	public Map<Integer,Integer>getEventsByViews();
	public List<String> displayBestEventsByViews() ;
	Event getEventById(int id);
	public void refundUsers(int ide);

}
