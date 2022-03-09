package tn.esprit.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.UnhealthyWord;



@Service
public interface IUnhealthwordservice {
	 public void removeWord(String word);

	    public boolean wordExists(String word);

	    public void deleteWord(String word);

	    public List<UnhealthyWord> getUnhealthyWordList();

		public void addWord(UnhealthyWord u);
}
