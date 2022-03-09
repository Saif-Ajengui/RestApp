package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.Participation;

public interface IParticipationService {

	 String addParticipation(int iduser, int idevent);
	List<Participation> participationsList();
	List<Participation> myParticipations();
}

