package tn.esprit.spring.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import tn.esprit.spring.entity.Offre;

@Repository

public interface IOffreRepository extends CrudRepository<Offre , Integer> {
	Offre findOById(int id);
	
	@Query ("SELECT o FROM Offre o WHERE o.partner.id=:id ")
	public List<Offre> getOffreByPartnerid(@Param ("id") int id);

}
