package tn.esprit.spring.controller;

import tn.esprit.spring.entity.Badge;
import tn.esprit.spring.entity.Evaluation;
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
@RequestMapping("/Evaluation")
public class EvaluationControl {
	
	@Autowired
	private EvaluationService evaluationService;
	
	//http://localhost:8082/examen/Evaluation/AjoutEvaluation
	@PostMapping("/AjoutEvaluation")
	public void ajoutEvaluation(@RequestBody Evaluation evaluation)
	{
		evaluationService.ajouterEvaluation(evaluation);
		
	}
	
	// http://localhost:8082/examen/Evaluation/Evaluations
	@GetMapping("/Evaluations")
	@ResponseBody
	public List<Evaluation> findAllEvaluations() {
		return evaluationService.getEvaluations();
	}
	// http://localhost:8082/examen/Evaluation/{id}
	@GetMapping("/{id}")
	@ResponseBody
	public Evaluation findEvaluationById(@PathVariable("id") int id) {
		return evaluationService.getEvaluationById(id);
	}
	
	// http://localhost:8082/examen/Evaluation/update
	@PutMapping("/update")
	@ResponseBody
	public Evaluation updateEvaluation(@RequestBody Evaluation evaluation) {
		return evaluationService.updateEvaluation(evaluation);
	}

	// http://localhost:8082/examen/Evaluation/delete/{id}
	@DeleteMapping("/delete/{id}")
	@ResponseBody
	public String deleteEvaluation(@PathVariable("id") int id) {
		return evaluationService.deleteEvaluation(id);
	}


}
