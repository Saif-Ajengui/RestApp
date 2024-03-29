package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.Offre;

public interface IOffreService {
	
	public Offre addOffre(Offre o) throws Exception;
	
	public String deleteOffre(int id) throws Exception;
	
	public Offre updateOffre(Offre o, int id) throws Exception;
	
	public List<Offre> getAllOffre();
	
	
	public List<Offre> getOffreByPartnerid(int id);
	
	public int CountOffreByPartner(int id);



}
