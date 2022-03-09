package tn.esprit.spring.service;

import java.io.IOException;
import java.util.List;

import tn.esprit.spring.entity.Question;


public interface IQuestionService {
	
	List<Question> retrieveAllQuestion();
	Question addQuestion(Question c) throws IOException;
	void deleteQuestion(Long id);
	Question updateQuestion(Question c);
	Question retrieveQuestion(Long id);
	
}
