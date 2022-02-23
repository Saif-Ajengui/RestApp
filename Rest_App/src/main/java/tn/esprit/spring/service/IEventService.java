package tn.esprit.spring.service;

import tn.esprit.spring.entity.Event;

public interface IEventService {
	
	public void addEvent(Event e);
	public void deleteEvent(int id);
	public void findEventById(int id);
	
}
