package tn.esprit.spring.repository;

import java.util.List;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Comment;






@Repository
public interface ICommentRepository extends CrudRepository< Comment , Integer> {
	
	
	@Modifying (clearAutomatically = true)
	@Query (value= "Delete from Comment c where c.id=:id")
	public void deleteById(@Param("id") int id);
	
	@Query("SELECT c FROM Comment c WHERE c.commentContent LIKE %?1%") 
	public List<Comment> findCommentsByTextContaining(String pattern);
	
	
	
	@Query("SELECT c FROM Comment c WHERE c.Publication.id =:id")
	public List<Comment> getCommentsByPostId(@Param("id")int id);
	

}