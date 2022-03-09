package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.Contrat;

public interface IContratService {

public Contrat addContrat(Contrat c ) throws Exception;
	
	public String deleteContrat(int id) throws Exception;
	
	public Contrat updateContrat(Contrat c, int id) throws Exception;
	
	public List<Contrat> getAllContrat();

	public List<Contrat> getContratElvee();
}
