package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.UnhealthyWord;
import tn.esprit.spring.repository.IUnhealthyWordRepository;



@Service
public class UnhealthyWordServiceImpl implements IUnhealthwordservice {
	@Autowired 
	IUnhealthyWordRepository unhealthyWordRepository; 
	
	 @Override
	    public void removeWord(String word) {
	        unhealthyWordRepository.deleteByWordIs(word);
	    }

	    @Override
	    public boolean wordExists(String word) {
	        return unhealthyWordRepository.existsByWord(word);
	    }

	    @Override
	    public void addWord(UnhealthyWord u) {
	            unhealthyWordRepository.save(u);
	    }

	    @Override
	    public void deleteWord(String word) {
	        unhealthyWordRepository.deleteByWordIs(word);
	    }

	    @Override
	    public List<UnhealthyWord> getUnhealthyWordList() {
	        return (List<UnhealthyWord>) unhealthyWordRepository.findAll();
	    }
	
	
	
}
