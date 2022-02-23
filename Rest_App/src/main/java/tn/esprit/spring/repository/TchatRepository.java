package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.TChat;

@Repository
public interface TchatRepository extends CrudRepository<TChat, Integer> {

}
