package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Contrat;

@Repository

public interface IContratRepository extends CrudRepository<Contrat , Integer> {
	Contrat findCById(int id);

}
