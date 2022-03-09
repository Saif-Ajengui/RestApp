package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Departement;


@Repository
public interface IDepartementRepository  extends CrudRepository<Departement, Long> {

}
