package tn.esprit.spring.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entity.Evaluation;
import tn.esprit.spring.entity.Score;
import tn.esprit.spring.repository.EvaluationRepo;
import tn.esprit.spring.repository.ScoreRepo;

@Slf4j
@Service
public class ScoreServiceImpl implements ScoreService{
	
	@Autowired
	private ScoreRepo scoreRepo;

	@Override
	public void ajouterScore(Score score) {
		// TODO Auto-generated method stub
		scoreRepo.save(score);
	}

	@Override
	public List<Score> getScores() {
		// TODO Auto-generated method stub
		return (List<Score>) scoreRepo.findAll();
	}

	@Override
	public Score getScoreById(int id) {
		// TODO Auto-generated method stub
		return scoreRepo.findById(id).get();
	}

	@Override
	public Score updateScore(Score score) {
		// TODO Auto-generated method stub
		Score b = scoreRepo.findById(score.getId()).orElse(null);
		b.setAvgscoreTask(score.getAvgscoreTask());
		b.setAvgscoreTask_Monthly(score.getAvgscoreTask_Monthly());
		b.setPercentDDLRespected(score.getPercentDDLRespected());
		b.setPercentreactivity(score.getPercentreactivity());
		b.setScoreProductivity(score.getScoreProductivity());
		b.setScoreEfficiency(score.getScoreEfficiency());
		
		
		return scoreRepo.save(b);
	}

	@Override
	public String deleteScore(int id) {
		// TODO Auto-generated method stub
		scoreRepo.deleteById(id);
		return "score "+id+" is deleted";
	}

}
