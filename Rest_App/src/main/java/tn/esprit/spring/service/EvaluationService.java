package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.Badge;
import tn.esprit.spring.entity.Evaluation;

public interface EvaluationService {

	void ajouterEvaluation(Evaluation evaluation);

	List<Evaluation> getEvaluations();

	Evaluation getEvaluationById(int id);

	Evaluation updateEvaluation(Evaluation evaluation);

	String deleteEvaluation(int id);


}
