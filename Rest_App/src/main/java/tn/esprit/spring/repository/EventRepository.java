package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;

import tn.esprit.spring.entity.Event;



public interface EventRepository extends CrudRepository<Event,Integer >{
	
	

}
