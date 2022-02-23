package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.Score;
import tn.esprit.spring.entity.Task;

public interface TaskService {

	void ajouterTask(Task task);

	List<Task> getTasks();

	Task getTaskById(int id);

	Task updateTask(Task task);

	String deleteTask(int id);

}
