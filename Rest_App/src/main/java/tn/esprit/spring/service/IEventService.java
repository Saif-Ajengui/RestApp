package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.Event;

public interface IEventService {
	
	public void addEvent(Event e);
	public void deleteEvent(int id);
	public Event findEventById(int id);
	public int updateEvent(Event e, int id) ;
	public List<Event>getAllEvents();
}
