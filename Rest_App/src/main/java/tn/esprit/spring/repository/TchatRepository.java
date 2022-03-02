package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.TChat;
import tn.esprit.spring.entity.User;

@Repository
public interface TchatRepository extends CrudRepository<TChat, Integer> {

	@Query("select t from TChat t where t.sender= :sender and t.receiver= :receiver")
	List<TChat> getTChat(@Param("sender") User sender, @Param("receiver") User receiver);
	
	@Query("select t from TChat t ORDER BY t.time ASC")
	List<TChat> getAllOrderByTime();
}
