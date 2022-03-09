package tn.esprit.spring.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.esprit.spring.entity.Event;
import tn.esprit.spring.entity.EventCategory;



public interface EventRepository extends CrudRepository<Event,Integer >{
	
	/*******************************UpdateEvent*******************************************/
	@Modifying
	@Transactional
	@Query("UPDATE Event e SET e.title = :title  , e.date = :date , e.hour = :hour , e.address =:address , e.description = :description "
			+ ", e.placesNbr = :numberOfPlaces ,e.category =:eventCategory ,e.ticketPrice = :priceTicket , e.status =:status, "
			+ " e.image = :image WHERE e.id = :id")
	
	public int updateEvent(@Param("title")String title,@Param("date")Date date,@Param("hour")Date hour,@Param("address")String address,
			@Param("description")String description,@Param("numberOfPlaces")int numberOfPlaces,@Param("eventCategory")EventCategory evc,
			@Param("priceTicket")float priceTicket,@Param("status")boolean status,@Param("image")byte[] image,@Param("id")int id	);
	
	
	/*************************Upcome Event****************************************/

	@Query("SELECT ev FROM Event ev WHERE ev.date >= CURRENT_DATE()")
	public List<Event> upcomingEvents();
	
	@Modifying
	@Transactional
	@Query("UPDATE Event p   SET p.views = :view+1 WHERE p.id =:id ")
	public int updateViewCountEvent(@Param("view")int view,@Param("id")int id);

}
