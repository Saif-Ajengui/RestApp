package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import tn.esprit.spring.entity.Offre;

import tn.esprit.spring.repository.IOffreRepository;

public class OffreServiceImp implements IOffreService{

	
	@Autowired
	 IOffreRepository OffreRepository;
	
	@Override
	public Offre addOffre(Offre o) throws Exception {
		OffreRepository.save(o);
		return o;
		
	}

	@Override
	public String deleteOffre(int id) throws Exception {
		OffreRepository.deleteById(id);
		return "Offre Deleted";
	}

	@Override
	public String updateOffre(Offre o, int id) throws Exception {
		Offre up = OffreRepository.findOById(id);
		up.setDate_DebutOffre(o.getDate_DebutOffre());
		up.setDate_FinOffre(o.getDate_FinOffre());
		up.setDescription(o.getDescription());
		up.setOffre_Value(o.getOffre_Value());
		return "Update Succes";
		
	}

	@Override
	public List<Offre> getAllPosts() {
		return  (List<Offre>) OffreRepository.findAll();
	}

}
