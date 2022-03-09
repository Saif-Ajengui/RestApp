package tn.esprit.spring.repository;

import java.util.List;

import javax.xml.stream.events.Comment;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import tn.esprit.spring.entity.Commentaire;



@Repository
public interface ICommentaireRepository extends CrudRepository< Commentaire , Integer> {
	
	
	@Modifying (clearAutomatically = true)
	@Query (value= "Delete from Commentaire c where c.id=:id")
	public void deleteById(@Param("id") int id);
	
	@Query("SELECT c FROM Commentaire c WHERE c.commentContent LIKE %?1%") 
	public List<Commentaire> findCommentsByTextContaining(String pattern);
	
	
	
	@Query("SELECT c FROM Commentaire c WHERE c.Publication.id =:id")
	public List<Commentaire> getCommentsByPostId(@Param("id")int id);
	

}
