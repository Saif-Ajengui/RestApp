package tn.esprit.spring.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entity.Score;
import tn.esprit.spring.entity.Task;
import tn.esprit.spring.repository.TaskRepo;

@Slf4j
@Service
public class TaskServiceImpl implements TaskService{
	
	@Autowired
	private TaskRepo taskRepo;

	@Override
	public void ajouterTask(Task task) {
		// TODO Auto-generated method stub
		taskRepo.save(task);
		
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

	@Override
	public Task updateTask(Task task) {
		// TODO Auto-generated method stub
		Task b = taskRepo.findById(task.getId()).orElse(null);
		b.setDdl(task.getDdl());
		b.setDescription(task.getDescription());
		b.setScoreTask(task.getScoreTask());
		b.setState(task.getState());
		b.setTitle(task.getTitle());
;
		
		
		return taskRepo.save(b);
	}

	@Override
	public String deleteTask(int id) {
		// TODO Auto-generated method stub
		taskRepo.deleteById(id);
		return "task "+id+" is deleted";
	}

}
