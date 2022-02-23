package tn.esprit.spring.controller;

import tn.esprit.spring.entity.Score;
import tn.esprit.spring.entity.Task;
import tn.esprit.spring.service.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/Task")
public class TaskControl {
	
	@Autowired
	private TaskService taskService;
	
	//http://localhost:8082/examen/Task/AjoutTask
	@PostMapping("/AjoutTask")
	public void AjoutTask(@RequestBody Task task)
	{
		taskService.ajouterTask(task);
	}
	
	// http://localhost:8082/examen/Task/Tasks
			@GetMapping("/Tasks")
			@ResponseBody
			public List<Task> findAllTasks() {
				return taskService.getTasks();
			}
			// http://localhost:8082/examen/Task/{id}
			@GetMapping("/{id}")
			@ResponseBody
			public Task findTaskById(@PathVariable("id") int id) {
				return taskService.getTaskById(id);
			}
			
			// http://localhost:8082/examen/Task/update
			@PutMapping("/update")
			@ResponseBody
			public Task updateTask(@RequestBody Task task) {
				return taskService.updateTask(task);
			}

			// http://localhost:8082/examen/Task/delete/{id}
			@DeleteMapping("/delete/{id}")
			@ResponseBody
			public String deleteTask(@PathVariable("id") int id) {
				return taskService.deleteTask(id);
			}

}
