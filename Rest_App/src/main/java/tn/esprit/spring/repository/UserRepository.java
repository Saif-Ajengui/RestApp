package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entity.User;

@Transactional
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {


	@Query("select u from User u where u.email = ?1")
	User findUserByEmail(String email);
	
	@Query("select u from User u where u.email = ?1")
	List<User> findUserByEmailExist(String email);
	
	
	@Query("select u from User u where u.id = ?1")
	User findUserById(@Param("id")int id);
	
	@Query("UPDATE User u SET u.approved = true WHERE u.id = ?1")
    @Modifying
    public void updateApp(int id);

	
	@Query("select u from User u where u.resettoken = ?1")
	User findUserByToken(@Param("resettoken")String token);
	
}
