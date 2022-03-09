package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.esprit.spring.entity.Event;
import tn.esprit.spring.entity.Participation;
import tn.esprit.spring.entity.User;



public interface ParticipationRepository extends CrudRepository<Participation, Integer> 
{

@Query("SELECT p FROM Participation p WHERE p.event=:event")
	
	List<Participation> Participations(@Param ("event") Event event);
	
	@Query("SELECT p FROM Participation p WHERE p.user=:user")
	List<Participation> myParticipations(@Param ("user") User user);
	
	
}
