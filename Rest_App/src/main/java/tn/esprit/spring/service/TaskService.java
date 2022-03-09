package tn.esprit.spring.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import tn.esprit.spring.entity.Task;
import tn.esprit.spring.entity.User;

public interface TaskService {
	
	Task ajouterTask(Task task);

	List<Task> getTasks();

	Task getTaskById(int id);
	
	Task updateTask(Task task);

	String deleteTask(int id);

	List<Integer> getId_SortDDL_ProcessingTasks();
	
	List<Task> getTask_SortDDL_ProcessingTasks();
	
	List<Task> getTask_SortDDL_ProcessingTasks_byProject(String pName);
	
	
	//Calendar getStartDate(int id);

	Calendar getFinishDate(int id);

	

	Task updateStartDate(int id, Calendar date);

	Task updatefinishDate(int id, Calendar date);

	Calendar calculate_FinishDate(int id);
	
	int getNb_Word_Days(int id);
/*
	



	User ddlExpandRequest(int idTask, int idUser, Calendar date);

	
*/

	void confirm(Task task);



	

	

	
}
