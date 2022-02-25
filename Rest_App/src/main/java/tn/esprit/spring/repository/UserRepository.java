package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.User;


@Repository
public interface UserRepository extends CrudRepository<User, Integer> {


	@Query("select u from User u where u.email = ?1")
	User findUserByEmail(String email);
	
	
	@Query("select u from User u where u.id = ?1")
	User findUserById(@Param("id")int id);

}
