package tn.esprit.spring.controller;
import tn.esprit.spring.entity.Evaluation;
import tn.esprit.spring.entity.Score;
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
@RequestMapping("/Score")
public class ScoreControl {
	
	@Autowired
	private ScoreService scoreService;
	
	//http://localhost:8082/examen/Score/AjoutScore
		@PostMapping("/AjoutScore")
		public void AjoutScore(@RequestBody Score score)
		{
			scoreService.ajouterScore(score);
		}
		
		// http://localhost:8082/examen/Score/Scores
		@GetMapping("/Scores")
		@ResponseBody
		public List<Score> findAllScores() {
			return scoreService.getScores();
		}
		// http://localhost:8082/examen/Score/{id}
		@GetMapping("/{id}")
		@ResponseBody
		public Score findEvaluationById(@PathVariable("id") int id) {
			return scoreService.getScoreById(id);
		}
		
		// http://localhost:8082/examen/Score/update
		@PutMapping("/update")
		@ResponseBody
		public Score updateScore(@RequestBody Score score) {
			return scoreService.updateScore(score);
		}

		// http://localhost:8082/examen/Score/delete/{id}
		@DeleteMapping("/delete/{id}")
		@ResponseBody
		public String deleteScore(@PathVariable("id") int id) {
			return scoreService.deleteScore(id);
		}

}
