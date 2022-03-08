package tn.esprit.spring.repository;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import tn.esprit.spring.entity.*;

public interface UserRepo extends CrudRepository<User,Integer>{
	
	@Query("SELECT distinct u.id FROM User u WHERE u.deptName = ?1")
	List<Integer> findByDept(String deptName);
/*

	
	@Query("SELECT u.task FROM User u WHERE u.id = ?1")
	List<Task> retrieveTasksByUser(int idUser);
*/


}
