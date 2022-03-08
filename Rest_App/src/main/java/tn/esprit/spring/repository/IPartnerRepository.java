package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import tn.esprit.spring.entity.Partner;



@Repository

public interface IPartnerRepository extends CrudRepository<Partner , Integer> {

	Partner findPById(int id);
	
	@Query("SELECT COUNT(*) FROM Partner")
	public List<Partner> getCountPartnerbyId(@Param ("id") int id);
	

}
