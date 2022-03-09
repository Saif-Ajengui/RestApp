package tn.esprit.spring.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Article;
import tn.esprit.spring.entity.Question;
import tn.esprit.spring.repository.QuestionRepository;




@Service
public class QuestionServiceImpl implements IQuestionService {
	
	@Autowired
	QuestionRepository QuestionRepository;

	
	public List<Question> retrieveAllQuestion() {
		List<Question> questions = (List<Question>) QuestionRepository.findAll();
		return questions;
	}

	public Question addQuestion(Question c) throws IOException {
		//c.setContent(cleanBadWords(c.getContent()));
		String Final=cleanContent(c);
		String jojo=cleanContent2(c);
		c.setContent(Final);
		c.setAnswer(jojo);
		return QuestionRepository.save(c) ;

	}

	
	public void deleteQuestion(Long id) {
		QuestionRepository.deleteById(id);
		
	}

	
	public Question updateQuestion(Question c) {
		return QuestionRepository.save(c) ;
	}

	
	public Question retrieveQuestion(Long id) {
		Question Question = QuestionRepository.findById(id).orElse(null) ;
		return Question;
	}
	
	
//Clean methods	
	public String cleanContent(Question a) throws IOException{
		List<String> stopwords = Files.readAllLines(Paths.get("stop_words_english.txt"));

		ArrayList<String> allWords = Stream.of(a.getContent().toLowerCase().split(" "))
				.collect(Collectors.toCollection(ArrayList<String>::new));
		
		allWords.removeAll(stopwords);
//FROM LIST TO STRING
		return allWords.stream().collect(Collectors.joining(" "));
	}
//--------------------------------------------------------------------------------
	
public String cleanContent2(Question a) throws IOException{
		List<String> stopwords = Files.readAllLines(Paths.get("stop_words_english.txt"));

		ArrayList<String> allTerms = Stream.of(a.getAnswer().toLowerCase().split(" "))
				.collect(Collectors.toCollection(ArrayList<String>::new));
		allTerms.removeAll(stopwords);
//FROM LIST TO STRING
		return allTerms.stream().collect(Collectors.joining(" "));
	}
	
}
