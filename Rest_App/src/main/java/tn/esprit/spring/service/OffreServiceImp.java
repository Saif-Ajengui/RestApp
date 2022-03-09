package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Offre;

import tn.esprit.spring.repository.IOffreRepository;

@Service

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
	public Offre updateOffre(Offre o, int id) throws Exception {
		Offre up = OffreRepository.findOById(id);
		up.setDate_DebutOffre(o.getDate_DebutOffre());
		up.setDate_FinOffre(o.getDate_FinOffre());
		up.setDescription(o.getDescription());
		up.setOffre_Value(o.getOffre_Value());
		
		return OffreRepository.save(up);
		
	}

	@Override
	public List<Offre> getAllOffre() {
		return  (List<Offre>) OffreRepository.findAll();
	}

	@Override
	public List<Offre> getOffreByPartnerid(int id) {
		
			return OffreRepository.getOffreByPartnerid(id);
	}

	@Override
	public int CountOffreByPartner(int id) {
		
		List<Offre> offres = (List<Offre>) OffreRepository.getOffreByPartnerid(id);
		return offres.size();
	}

	

}
