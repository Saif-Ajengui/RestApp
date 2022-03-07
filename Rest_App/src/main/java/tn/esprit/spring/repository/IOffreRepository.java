package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Partner;

import tn.esprit.spring.entity.Offre;

@Repository

public interface IOffreRepository extends CrudRepository<Offre , Integer> {
	Offre findOById(int id);
}
