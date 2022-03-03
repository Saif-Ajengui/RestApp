package tn.esprit.spring.repository;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import tn.esprit.spring.entity.Task;


public interface TaskRepo extends CrudRepository<Task,Integer> {
	
	@Query(value = "SELECT DISTINCT t.id FROM tn.esprit.spring.entity.Task t WHERE t.state ='Waiting' or t.state ='Missed' or t.state ='To_Redo' ORDER BY ddl")    
	public List<Integer> triDDL();

	@Query(value = "SELECT t.startDate FROM tn.esprit.spring.entity.Task t WHERE t.id = ?1")
	public Calendar retrieveStartDateById(int id);
	
	@Query(value = "SELECT t.finishDate FROM tn.esprit.spring.entity.Task t WHERE t.id = ?1")
	public Calendar retrieveFinishDateById(int id);

	@Query(value = "SELECT DISTINCT t.nbWorkDaysValued FROM tn.esprit.spring.entity.Task t WHERE t.id = ?1")
	public int getNb_Word_Days(int id);

	

}
