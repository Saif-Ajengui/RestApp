package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import tn.esprit.spring.entity.Contrat;
import tn.esprit.spring.entity.Offre;
import tn.esprit.spring.repository.IContratRepository;

public class ContratServiceImp implements IContratService {
	
	@Autowired
	
	IContratRepository ContratRepository;

	@Override
	public Contrat addContrat(Contrat c) throws Exception {
		ContratRepository.save(c);
		return c;
	}

	@Override
	public String deleteContrat(int id) throws Exception {
		ContratRepository.deleteById(id);
		return "Contrat Deleted";
	}

	@Override
	public String updateContrat(Contrat c, int id) throws Exception {
		Contrat upd = ContratRepository.findCById(id);
		upd.setDate_Debut(c.getDate_Debut());
		upd.setDate_Fin(c.getDate_Fin());
		upd.setContract_value(c.getContract_value());
		return "Update Succes";
	}

	@Override
	public List<Contrat> getAllPosts() {
		return  (List<Contrat>) ContratRepository.findAll();
	}

}
