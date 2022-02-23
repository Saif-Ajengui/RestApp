package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.Evaluation;
import tn.esprit.spring.entity.Score;

public interface ScoreService {

	void ajouterScore(Score score);

	List<Score> getScores();

	Score getScoreById(int id);

	Score updateScore(Score score);

	String deleteScore(int id);

}
