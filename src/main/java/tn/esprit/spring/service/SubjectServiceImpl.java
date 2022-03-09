package tn.esprit.spring.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import tn.esprit.spring.entity.Subject;
import tn.esprit.spring.repository.SubjectRepository;




@Service
public class SubjectServiceImpl implements ISubjectService {
	
	@Autowired
	SubjectRepository SubjectRepository;

	
	public List<Subject> retrieveAllSubject() {
		List<Subject> Subjects = (List<Subject>) SubjectRepository.findAll();
		return Subjects;
	}

	public Subject addSubject(Subject c) {
		return	SubjectRepository.save(c) ;
		
	}

	
	public void deleteSubject(Long id) {
		SubjectRepository.deleteById(id);
		
	}

	
	public Subject updateSubject(Subject c) {
		return SubjectRepository.save(c) ;
	}

	
	public Subject retrieveSubject(Long id) {
		Subject Subject = SubjectRepository.findById(id).orElse(null) ;
		return Subject;
	}
	
	



	
}
