package tn.esprit.spring.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import tn.esprit.spring.entity.Score;
import tn.esprit.spring.entity.Task;

public interface TaskService {

	void ajouterTask(Task task);

	List<Task> getTasks();

	Task getTaskById(int id);
	
	Calendar getStartDate(int id);
	
	Calendar getFinishDate(int id);

	Task updateTask(Task task);

	String deleteTask(int id);
	
	List<Integer> trierparddl();

	Task updateStartDate(int id, Calendar date);

	Task updatefinishDate(int id, Calendar date);

//	Task determinFinishDate(int id);

	

	//void assignTaskToUser(int idTask, int idUser);

//	void schedulingtaskGREEDY_METHOD();
	Calendar calculate_FinishDate(int id);

	
	
	
	

}
