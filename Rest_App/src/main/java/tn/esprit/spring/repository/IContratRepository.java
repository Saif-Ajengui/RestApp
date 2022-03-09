package tn.esprit.spring.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Contrat;

@Repository

public interface IContratRepository extends CrudRepository<Contrat , Integer> {
	Contrat findCById(int id);
	
	@Query("SELECT c FROM Contrat c order by c.contract_value desc")
	
	List<Contrat> ContratElevee();
}
