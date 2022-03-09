package tn.esprit.spring.controller;

import tn.esprit.spring.entity.Task;
import tn.esprit.spring.service.*;

import java.util.Calendar;
import java.util.List;

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
	public Task AjoutTask(@RequestBody Task task) {
		return taskService.ajouterTask(task);
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

	// http://localhost:8082/examen/Task/getId_SortDDL_ProcessingTasks
	@GetMapping("/getId_SortDDL_ProcessingTasks")
	@ResponseBody
	public List<Integer> getId_SortDDL_ProcessingTasks() {
		return taskService.getId_SortDDL_ProcessingTasks();
	}

	// http://localhost:8082/examen/Task/getTask_SortDDL_ProcessingTasks
	@GetMapping("/getTask_SortDDL_ProcessingTasks")
	@ResponseBody
	public List<Task> getTask_SortDDL_ProcessingTasks() {
		return taskService.getTask_SortDDL_ProcessingTasks();

	}

	// http://localhost:8082/examen/Task/getTask_SortDDL_ProcessingTasks_byProject/pName
	@GetMapping("/getTask_SortDDL_ProcessingTasks_byProject/{pName}")
	@ResponseBody
	public List<Task> getTask_SortDDL_ProcessingTasks_byProject(@PathVariable("pName") String pName) {
		if (pName.equals(null)) {
			return null;
		} else {
			return taskService.getTask_SortDDL_ProcessingTasks_byProject(pName);
		}
	}

	// http://localhost:8082/examen/Task/toStringTask_SortDDL_ProcessingTasks_byProject/pName
	@GetMapping("/toStringTask_SortDDL_ProcessingTasks_byProject/{pName}")
	@ResponseBody
	public String toStringTask_SortDDL_ProcessingTasks_byProject(@PathVariable("pName") String pName)
			throws NullPointerException {
		String res = "";
		try {
			List<Task> tasks = taskService.getTask_SortDDL_ProcessingTasks_byProject(pName);
			for (int i = 0; i < tasks.size(); i++) {
				res = res + tasks.get(i).toString() + ",\n";
			}

			return "here's the " + pName + " projet tasks scheduling:\n" + res;
		} catch (NullPointerException e) {
			e.printStackTrace();
			return "Null pointer Exception, please check the tasks scheduling";
		}

	}

	// TODO

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
		// Calendar f_d =
		return taskService.calculate_FinishDate(id);
		// return taskService.updatefinishDate(id, f_d);
	}

	// http://localhost:8082/examen/Task/mesurement_scheduleTask_GREEDY_METHOD/date
	@PutMapping("/mesurement_scheduleTask_GREEDY_METHOD/{date}")
	@ResponseBody
	public String mesurement_scheduleTask_GREEDY_METHOD(
			@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Calendar date) {
		List<Task> sortedTasks = taskService.getTask_SortDDL_ProcessingTasks();
		Calendar x = null;
		int dure = 0;
		int nb = sortedTasks.size();
		for (int i = 0; i < nb; i++) {
			dure += (sortedTasks.get(i).getNbWorkDaysValued());

		}
		int a = nb - 1;
		x = sortedTasks.get(a).getDdl();
		// set(x.get(Calendar.YEAR), x.get(Calendar.MONTH),
		// x.get(Calendar.DAY_OF_MONTH));
		// int amount = -dure;
		x.add(Calendar.MONTH, +1);
		x.add(Calendar.DAY_OF_MONTH, -dure);
		date.add(Calendar.MONTH, +1);
		String explanation = "EXPLANATION:" + "\n * The optimal work flow start date: " + x.get(Calendar.YEAR) + "-"
				+ x.get(Calendar.MONTH) + "-" + x.get(Calendar.DAY_OF_MONTH)
				+ "\n ==>Optimized maximum latenss & optimized schedule & deadline respected ==>The best Case Practice for productivity with maximum lateness"
				+ "\n * If inserted date before " + x.get(Calendar.YEAR) + "-" + x.get(Calendar.MONTH) + "-"
				+ x.get(Calendar.DAY_OF_MONTH)
				+ "\n ==>High maximum latenss & not optimized schedule  & deadline respected ==>Not the best Case Practice for productivity with maximum lateness but accepted for heavy projects"
				+ "\n * If inserted date after " + x.get(Calendar.YEAR) + "-" + x.get(Calendar.MONTH) + "-"
				+ x.get(Calendar.DAY_OF_MONTH)
				+ "\nThe suggested date will cause a delay!! please insert an earlier date or review the tasks lists";
		if (date.after(x)) {
			return "The result of the date" + date.get(Calendar.YEAR) + "-" + date.get(Calendar.MONTH) + "-"
					+ date.get(Calendar.DAY_OF_MONTH)
					+ " ->Will cause a delay!! please insert an earlier date or review the tasks list\n" + explanation;

		} else if (date.before(x)) {
			return "The result of the date" + date.get(Calendar.YEAR) + "-" + date.get(Calendar.MONTH) + "-"
					+ date.get(Calendar.DAY_OF_MONTH)
					+ " ->Not the best Case Practice for productivity with maximum lateness but accepted for heavy projects ==> High maximum latenss & lazy schedule  & deadline respected\n"
					+ explanation;
		} else if (date.equals(x)) {
			return "The result of the date" + date.get(Calendar.YEAR) + "-" + date.get(Calendar.MONTH) + "-"
					+ date.get(Calendar.DAY_OF_MONTH)
					+ " ->Best Case Practice for productivity with maximum lateness ==> Optimized maximum latenss & optimized schedule & deadline respected\n"
					+ explanation;
		} else {
			return explanation;
		}

	}

	// http://localhost:8082/examen/Task/mesurement_scheduleTask_GREEDY_METHOD_ByProject/date/pName
	@PutMapping(value = "/mesurement_scheduleTask_GREEDY_METHOD_ByProject/{date}/{pName}")
	@ResponseBody
	public String mesurement_scheduleTask_GREEDY_METHOD_ByProject(
			@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Calendar date,
			@PathVariable("pName") String pName) {
		List<Task> sortedTasks = taskService.getTask_SortDDL_ProcessingTasks_byProject(pName);
		Calendar x = null;
		int dure = 0;
		int nb = sortedTasks.size();
		for (int i = 0; i < nb; i++) {
			dure += (sortedTasks.get(i).getNbWorkDaysValued());

		}
		int a = nb - 1;
		x = sortedTasks.get(a).getDdl();
		// set(x.get(Calendar.YEAR), x.get(Calendar.MONTH),
		// x.get(Calendar.DAY_OF_MONTH));
		// int amount = -dure;
		x.add(Calendar.MONTH, +1);
		x.add(Calendar.DAY_OF_MONTH, -dure);
		date.add(Calendar.MONTH, +1);
		String explanation = "EXPLANATION:" + "\n * The optimal work flow start date: " + x.get(Calendar.YEAR) + "-"
				+ x.get(Calendar.MONTH) + "-" + x.get(Calendar.DAY_OF_MONTH)
				+ "\n ==>Optimized maximum latenss & optimized schedule & deadline respected ==>The best Case Practice for productivity with maximum lateness"
				+ "\n * If inserted date before " + x.get(Calendar.YEAR) + "-" + x.get(Calendar.MONTH) + "-"
				+ x.get(Calendar.DAY_OF_MONTH)
				+ "\n ==>High maximum latenss & not optimized schedule  & deadline respected ==>Not the best Case Practice for productivity with maximum lateness but accepted for heavy projects"
				+ "\n * If inserted date after " + x.get(Calendar.YEAR) + "-" + x.get(Calendar.MONTH) + "-"
				+ x.get(Calendar.DAY_OF_MONTH)
				+ "\nThe suggested date will cause a delay!! please insert an earlier date or review the tasks lists";
		if (date.after(x)) {
			return "The result of the date" + date.get(Calendar.YEAR) + "-" + date.get(Calendar.MONTH) + "-"
					+ date.get(Calendar.DAY_OF_MONTH)
					+ " ->Will cause a delay!! please insert an earlier date or review the tasks list\n" + explanation;

		} else if (date.before(x)) {
			return "The result of the date" + date.get(Calendar.YEAR) + "-" + date.get(Calendar.MONTH) + "-"
					+ date.get(Calendar.DAY_OF_MONTH)
					+ " ->Not the best Case Practice for productivity with maximum lateness but accepted for heavy projects ==> High maximum latenss & lazy schedule  & deadline respected\n"
					+ explanation;
		} else if (date.equals(x)) {
			return "The result of the date" + date.get(Calendar.YEAR) + "-" + date.get(Calendar.MONTH) + "-"
					+ date.get(Calendar.DAY_OF_MONTH)
					+ " ->Best Case Practice for productivity with maximum lateness ==> Optimized maximum latenss & optimized schedule & deadline respected\n"
					+ explanation;
		} else {
			return explanation;
		}

	}

	// http://localhost:8082/examen/Task/getMesurement_scheduleTask_GREEDY_METHOD_ByProject/pName
	@GetMapping(value = "/getMesurement_scheduleTask_GREEDY_METHOD_ByProject/{pName}")
	@ResponseBody
	public String getMesurement_scheduleTask_GREEDY_METHOD_ByProject(@PathVariable("pName") String pName) {
		List<Task> sortedTasks = taskService.getTask_SortDDL_ProcessingTasks_byProject(pName);
		Calendar x = null;
		int dure = 0;
		int nb = sortedTasks.size();
		/*
		 * for (int i = 0; i < nb; i++) { dure +=
		 * (sortedTasks.get(i).getNbWorkDaysValued());
		 * 
		 * }
		 */
		int a = nb - 1;
		x = sortedTasks.get(a).getDdl();
		// set(x.get(Calendar.YEAR), x.get(Calendar.MONTH),
		// x.get(Calendar.DAY_OF_MONTH));
		// int amount = -dure;
		x.add(Calendar.MONTH, +1);
		x.add(Calendar.DAY_OF_MONTH, -dure);

		String explanation = "\n * The optimal work flow start date: " + x.get(Calendar.YEAR) + "-"
				+ x.get(Calendar.MONTH) + "-" + x.get(Calendar.DAY_OF_MONTH)
				+ "\n ==>Optimized maximum latenss & optimized schedule & deadline respected ==>The best Case Practice for productivity with maximum lateness"
				+ "\n * If inserted date before " + x.get(Calendar.YEAR) + "-" + x.get(Calendar.MONTH) + "-"
				+ x.get(Calendar.DAY_OF_MONTH)
				+ "\n ==>High maximum latenss & not optimized schedule  & deadline respected ==>Not the best Case Practice for productivity with maximum lateness but accepted for heavy projects"
				+ "\n * If inserted date after " + x.get(Calendar.YEAR) + "-" + x.get(Calendar.MONTH) + "-"
				+ x.get(Calendar.DAY_OF_MONTH)
				+ "\nThe suggested date will cause a delay!! please insert an earlier date or review the tasks lists";
		return explanation;

	}

	// http://localhost:8082/examen/Task/test_scheduleTask_GREEDY_METHOD/date
	@PutMapping(value = "/test_scheduleTask_GREEDY_METHOD/{date}")
	public List<Task> test_scheduleTask_GREEDY_METHOD(
			@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Calendar date) {

		List<Integer> sortedWaitingTasks = taskService.getId_SortDDL_ProcessingTasks();
		int nb = sortedWaitingTasks.size();
		Calendar s_d = date;
		for (int i = 0; i < nb; i++) {
			int id = sortedWaitingTasks.get(i);
			Task t = taskService.updateStartDate(id, s_d);
			Calendar f_d = taskService.calculate_FinishDate(id);
			t = taskService.updatefinishDate(id, f_d);
			s_d = taskService.calculate_FinishDate(id); // la suivante s_d = f_d
														// actuel

		}
		return taskService.getTask_SortDDL_ProcessingTasks();

	}

	// http://localhost:8082/examen/Task/test_scheduleTask_GREEDY_METHOD_byProject/date/pName
	@PutMapping(value = "/test_scheduleTask_GREEDY_METHOD_byProject/{date}/{pName}")
	public List<Task> test_scheduleTask_GREEDY_METHOD_byProject(
			@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Calendar date,
			@PathVariable("pName") String pName) {

		List<Task> sortedWaitingTasks = taskService.getTask_SortDDL_ProcessingTasks_byProject(pName);
		int nb = sortedWaitingTasks.size();
		Calendar s_d = date;
		for (int i = 0; i < nb; i++) {
			int id = sortedWaitingTasks.get(i).getId();
			Task t = taskService.updateStartDate(id, s_d);
			Calendar f_d = taskService.calculate_FinishDate(id);
			t = taskService.updatefinishDate(id, f_d);
			s_d = taskService.calculate_FinishDate(id); // la suivante s_d = f_d
														// actuel

		}
		return sortedWaitingTasks;

	}

	// http://localhost:8082/examen/Task/confirm_scheduleTask_GREEDY_METHOD_byProject/date/pName
	@PutMapping("/confirm_scheduleTask_GREEDY_METHOD_byProject/{date}/{pName}")
	public List<Task> confirm_scheduleTask_GREEDY_METHOD_byProject(
			@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Calendar date,
			@PathVariable("pName") String pName) {

		List<Task> t = this.test_scheduleTask_GREEDY_METHOD_byProject(date, pName);
		int nb = t.size();
		for (int i = 0; i < nb; i++) {
			taskService.confirm(t.get(i));
		}
		return t;
	}
	

	/*
	 * 
	 * 
	 * 
	 * //////////////// TEST // http://localhost:8082/examen/Task/tasti
	 * 
	 * @GetMapping("/tasti") public Calendar tasti() {
	 * 
	 * return Calendar.getInstance(TimeZone.getTimeZone("GMT+01:00"));//
	 * TimeZone.getTimeZone("GMT+01:00"); // .getAvailableIDs(); }
	 * 
	 * 
	 * 
	 * 
	 */

}
