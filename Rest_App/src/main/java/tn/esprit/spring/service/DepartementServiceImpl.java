package tn.esprit.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Commentaire;
import tn.esprit.spring.entity.Departement;
import tn.esprit.spring.repository.IDepartementRepository;


@Service
public class DepartementServiceImpl implements IDepartementService {
	@Autowired 
	IDepartementRepository DepartementRepository;
	@Override
	public String addDepartement(Departement d) throws Exception {
		DepartementRepository.save(d);
		return ("Departement ajouter"); 
		
	}

	@Override
	public String deleteDepartement(long id) throws Exception {
		 DepartementRepository.deleteById((long) id);
		  return "Departement supprimer";
	}

	@Override
	public String updateDepartement(Departement d, long id) throws Exception {
		Departement d1= DepartementRepository.findById((long) id).get();
	d1.setName(d.getName());
		DepartementRepository.save(d1);
		return ("Departement updated successfully");
	}

	@Override
	public List<Departement> getAllDepartements() {
		List<Departement>Departements = new ArrayList<Departement>();
		DepartementRepository.findAll().forEach(e ->Departements.add(e));
		return Departements;
	}
	
	

}
