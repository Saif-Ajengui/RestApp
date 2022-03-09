package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.esprit.spring.entity.Commentaire;

public interface CommentaireRepository extends CrudRepository<Commentaire, Integer> {

	@Modifying (clearAutomatically = true)
	@Query (value= "Delete from Commentaire c where c.id= :id")
	public void deleteById(@Param("id") int id);
	
	@Query("SELECT c FROM Commentaire c WHERE c.commentContent LIKE %?1%") 
	public List<Commentaire> findCommentsByTextContaining(String pattern);
	
	@Query("SELECT c FROM Commentaire c WHERE c.user.id =:id")
	public List<Commentaire> getCommentsByUserId(@Param("id")int id);
	
	@Query("SELECT c FROM Commentaire c WHERE c.event.id =:id")
	public List<Commentaire> getCommentsByEventId(@Param("id")int id);
}
