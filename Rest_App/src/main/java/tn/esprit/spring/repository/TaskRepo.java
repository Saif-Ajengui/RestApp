package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;

import tn.esprit.spring.entity.Task;


public interface TaskRepo extends CrudRepository<Task,Integer> {

}
