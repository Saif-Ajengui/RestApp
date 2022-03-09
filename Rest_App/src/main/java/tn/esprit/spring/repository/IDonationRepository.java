package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.esprit.spring.entity.Donation;
import tn.esprit.spring.entity.Event;




public interface IDonationRepository extends CrudRepository<Donation,Integer >{
	
	@Query("SELECT d FROM Donation d WHERE d.event=:event ")
	List<Donation> DonationOfEvent(@Param("event") Event event);
	

}
