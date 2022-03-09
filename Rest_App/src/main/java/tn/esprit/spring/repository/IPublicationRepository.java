package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import tn.esprit.spring.entity.Publication;



@Repository
public interface IPublicationRepository extends CrudRepository<Publication, Integer> {

	@Query("SELECT p FROM Publication p WHERE p.postContent LIKE %?1%  order by p.createDate desc")
	List<Publication> findPostsByTextContaining(String pattern);
	@Query("SELECT p FROM Publication p WHERE p.user.id =?1")
	List<Publication>findPostsByUserid(int id);
	@Query("SELECT p FROM Publication p WHERE p.Departement.id =?1")
	List<Publication>findPostsByDepartementid(long id);
	
}
