package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.Question;
import tn.esprit.spring.entity.Subject;


public interface ISubjectService {
	
	List<Subject> retrieveAllSubject();
	Subject addSubject(Subject s);
	void deleteSubject(Long id);
	Subject updateSubject(Subject c);
	Subject retrieveSubject(Long id);
	
}

