package tn.esprit.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.repository.CalendarrRepo;
import tn.esprit.spring.repository.TaskRepo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CalendarrServiceImpl implements CalendarrService{
	
	@Autowired
	private CalendarrRepo calendarrRepo;
/*
	@Override
	public void schedulingtaskGREEDY_METHOD() {
		// TODO Auto-generated method stub
		
	}
	
	*/
	
	

}
