package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Commentaire;



@Repository
public interface ICommentaireRepository extends CrudRepository< Commentaire , Long> {

}
