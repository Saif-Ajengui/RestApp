package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;

import tn.esprit.spring.entity.User;



public interface IUserRepository extends CrudRepository<User, Integer> {

}
