package tn.esprit.spring.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entity.Badge;
import tn.esprit.spring.entity.Evaluation;
import tn.esprit.spring.repository.BadgeRepo;
import tn.esprit.spring.repository.EvaluationRepo;

@Slf4j
@Service
public class EvaluationServiceImpl implements EvaluationService{

	@Autowired
	private EvaluationRepo evaluationRepo;

	@Override
	public void ajouterEvaluation(Evaluation evaluation) {
		// TODO Auto-generated method stub
		evaluationRepo.save(evaluation);
		
	}

	@Override
	public List<Evaluation> getEvaluations() {
		// TODO Auto-generated method stub
		return (List<Evaluation>) evaluationRepo.findAll();
	}

	@Override
	public Evaluation getEvaluationById(int id) {
		// TODO Auto-generated method stub
		return evaluationRepo.findById(id).get();
	}

	@Override
	public Evaluation updateEvaluation(Evaluation evaluation) {
		// TODO Auto-generated method stub
		Evaluation b = evaluationRepo.findById(evaluation.getId()).orElse(null);
		b.setPositive_feedback(evaluation.getPositive_feedback());
		b.setNegative_feedback(evaluation.getNegative_feedback());
		b.setRecommendation(evaluation.getRecommendation());
		b.setScore_behavior(evaluation.getScore_behavior());
		b.setToDo(evaluation.getToDo());
		
		
		return evaluationRepo.save(b);
	}

	@Override
	public String deleteEvaluation(int id) {
		// TODO Auto-generated method stub
		evaluationRepo.deleteById(id);
		return "evaluation "+id+" is deleted";
	}






}
