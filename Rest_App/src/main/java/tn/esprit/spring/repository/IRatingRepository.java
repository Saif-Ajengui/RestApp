package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.esprit.spring.entity.Rating;



public interface IRatingRepository  extends CrudRepository<Rating, Integer>{
	
	@Query("SELECT e FROM Rating e WHERE e.event.id=:idEvenement AND e.user.id=:id ")
	public List<Rating> findEvaluationByUserAndEvent(@Param("idEvenement")int idProd,@Param("id")int idUser);

	@Query("SELECT COUNT(e.idEvalution) FROM Rating e WHERE e.event.id =:idp")
	int countEvaluation(@Param("idp")int idp);
	@Query("SELECT COUNT(e.idEvalution) FROM Rating e WHERE e.event.id =:idp AND e.user.id =:id")
	int countRatingUserByEvent(@Param("idp")int idp,@Param("id")int iduser);
	
	@Query("SELECT rate FROM Rating r WHERE r.event.idEvenement =:id")
	List<Integer>getEvaluationByEvent(@Param("id")int ide);
	
	
}

