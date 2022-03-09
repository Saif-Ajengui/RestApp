package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Contrat;

import tn.esprit.spring.repository.IContratRepository;
import tn.esprit.spring.repository.IPartnerRepository;

@Service


public class ContratServiceImp implements IContratService {
	
	@Autowired
	
	IContratRepository ContratRepository;

	@Autowired 
	IPartnerRepository PartnerRep;

	@Override
	public Contrat addContrat(Contrat c ) throws Exception {
	
		ContratRepository.save(c);
		return c;
	}

	@Override
	public String deleteContrat(int id) throws Exception {
		ContratRepository.deleteById(id);
		return "Contrat Deleted";
	}

	@Override
	public Contrat updateContrat(Contrat c, int id) throws Exception {
		Contrat upd = ContratRepository.findCById(id);
		upd.setDate_Debut(c.getDate_Debut());
		upd.setDate_Fin(c.getDate_Fin());
		upd.setContract_value(c.getContract_value());
		return ContratRepository.save(upd);
	}

	@Override
	public List<Contrat> getAllContrat() {
		return  (List<Contrat>) ContratRepository.findAll();
	}

	@Override
	public List<Contrat> getContratElvee() {
		return (List<Contrat>) ContratRepository.ContratElevee();		
	}
	
	

}
