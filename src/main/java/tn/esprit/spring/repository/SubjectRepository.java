package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Subject;


public interface SubjectRepository extends JpaRepository<Subject,Long> {
	

	
	//List<Facture> getFacturesByClient(Long id);
	

	
}
