package tn.esprit.spring.controller;

import tn.esprit.spring.entity.Task;
import tn.esprit.spring.service.*;

import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

	// http://localhost:8082/examen/Task/AjoutTask
	@PostMapping("/AjoutTask")
	public void AjoutTask(@RequestBody Task task) {
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

	// http://localhost:8082/examen/Task/SortDDL_ProcessingTasks
	@GetMapping("/SortDDL_ProcessingTasks")
	@ResponseBody
	public List<Integer> SortDDL_ProcessingTasks() {
		return taskService.trierparddl();
	}

	// http://localhost:8082/examen/Task/updateStartDate/id/{yyyy-mm-dd
	@PutMapping("/updateStartDate/{id}/{date}")
	@ResponseBody
	public Task updateStartDate(@PathVariable("id") int id,
			@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Calendar date) {
		return taskService.updateStartDate(id, date);
	}

	// http://localhost:8082/examen/Task/updateFinishDate/id/{yyyy-mm-dd
	@PutMapping("/updateFinishDate/{id}/{date}")
	@ResponseBody
	public Task updateFinishDate(@PathVariable("id") int id,
			@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Calendar date) {
		return taskService.updatefinishDate(id, date);
	}

	// http://localhost:8082/examen/Task/getFinishDate/id
	@GetMapping("/getFinishDate/{id}")
	@ResponseBody
	public Calendar getFinishDate(@PathVariable("id") int id) {
		return taskService.getFinishDate(id);
	}

	// http://localhost:8082/examen/Task/determinFinishDate/id
	@GetMapping("/determinFinishDate/{id}")
	@ResponseBody
	public Calendar determinFinishDate(@PathVariable("id") int id) {
		//Calendar f_d =
		return taskService.calculate_FinishDate(id);
		//return taskService.updatefinishDate(id, f_d);
	}

	
	// http://localhost:8082/examen/Task/scheduletaskGREEDY_METHOD
	@Transactional
	@PutMapping(value = "/scheduletaskGREEDY_METHOD/{date}")
	public Calendar scheduletaskGREEDY_METHOD(
			@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Calendar date) {
		List<Integer> sortedWaitingTasks = taskService.trierparddl();
		int nb = sortedWaitingTasks.size();
		Calendar s_d = date;
		for (int i = 0; i < nb; i++) {
			int id = sortedWaitingTasks.get(i);
			Task t = taskService.updateStartDate(id, s_d);
			Calendar f_d = taskService.calculate_FinishDate(id);
			t = taskService.updatefinishDate(id, f_d);
			s_d = taskService.calculate_FinishDate(id); //la suivante s_d = f_d actuel
			

		}

		return null;
		// taskService.schedulingtaskGREEDY_METHOD();
	}

}


