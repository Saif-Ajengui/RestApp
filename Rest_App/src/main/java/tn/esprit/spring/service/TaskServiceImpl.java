package tn.esprit.spring.service;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entity.Task;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.TaskRepo;
import tn.esprit.spring.repository.UserRepo;

@Slf4j
@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepo taskRepo;

	@Autowired
	private UserRepo userRepo;

	@Override
	public Task ajouterTask(Task task) {
		// TODO Auto-generated method stub
		return taskRepo.save(task);

	}

	@Override
	public List<Task> getTasks() {
		// TODO Auto-generated method stub
		return (List<Task>) taskRepo.findAll();
	}

	@Override
	public Task getTaskById(int id) {
		// TODO Auto-generated method stub
		return taskRepo.findById(id).get();
	}

	public List<Integer> getId_SortDDL_ProcessingTasks() {

		return taskRepo.getIdTriDDLProcessing();

	}

	@Override
	public List<Task> getTask_SortDDL_ProcessingTasks() {
		// TODO Auto-generated method stub
		return taskRepo.getTaskTriDDLProcessing();
	}
	
	@Override
	public List<Task> getTask_SortDDL_ProcessingTasks_byProject(String pName) {
		// TODO Auto-generated method stub
		return taskRepo.getTaskTriDDLProcessing_byProject(pName);
		
	}


	@Override
	public Task updateTask(Task task) {
		// TODO Auto-generated method stub
		Task b = taskRepo.findById(task.getId()).orElse(null);
		b.setProject(task.getProject());
		b.setTitle(task.getTitle());
		b.setDdl(task.getDdl());
		b.setNbWorkDaysValued(task.getNbWorkDaysValued());
		b.setDescription(task.getDescription());
		b.setAcceptAfterDDL(task.isAcceptAfterDDL());
		// b.setScoreTask(task.getScoreTask());
		b.setState(task.getState());

		return taskRepo.save(b);
	}

	@Override
	public String deleteTask(int id) {
		// TODO Auto-generated method stub
		taskRepo.deleteById(id);
		return "task " + id + " is deleted";
	}

	// @Override public Calendar getStartDate(int id) { // TODO Auto-generated
	// method stub return taskRepo.retrieveStartDateById(id); }

	@Override
	public Calendar getFinishDate(int id) {
		return taskRepo.retrieveFinishDateById(id);
	}

	@Override
	public Task updateStartDate(int id, Calendar date) {
		Task b = taskRepo.findById(id).orElse(null);
		b.setStartDate(date);
		return taskRepo.save(b);
	}

	@Override
	public Task updatefinishDate(int id, Calendar date) {
		Task b = taskRepo.findById(id).orElse(null);
		b.setFinishDate(date);
		return taskRepo.save(b);
	}

	@Override
	public Calendar calculate_FinishDate(int id) {
		Calendar aux = taskRepo.retrieveStartDateById(id);
		int nb_work_days = taskRepo.getNb_Word_Days(id);
		aux.add(Calendar.DAY_OF_MONTH, nb_work_days);

		return aux;
	}

	@Override
	public int getNb_Word_Days(int id) {
		return taskRepo.getNb_Word_Days(id);
	}
	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @Transactional
	 * 
	 * @Override public User ddlExpandRequest(int idTask, int idUser, Calendar
	 * date) { // TODO Auto-generated method stub Task t = null; User u =
	 * userRepo.findById(idUser).orElse(null);
	 * 
	 * List<Task> tasksOfUser = u.getTask(); Task res = null; int nbT =
	 * tasksOfUser.size(); int i = 0;
	 * 
	 * while(tasksOfUser.get(i).getId() != idTask) {
	 * 
	 * // u.setTask(task); res = tasksOfUser.get(i); i++; }
	 * 
	 * 
	 * while(u.getTask().get(i).getId() != idTask) {
	 * 
	 * // u.setTask(task); res = tasksOfUser.get(i); i++; }
	 * u.getTask().get(i).setDdlExpanded(date);
	 * 
	 * 
	 * return u; //return tasksOfUser.get(i); // t.setDdlExpanded(ddlExpanded);
	 * }
	 * 
	 */

	@Override
	public void confirm(Task task) {
		// TODO Auto-generated method stub
		Task b = taskRepo.findById(task.getId()).orElse(null);
		b.setStartDateConfirm(task.getStartDate());
		b.setFinishDateConfirm(task.getStartDate());
		taskRepo.save(b);
		
	}




}
