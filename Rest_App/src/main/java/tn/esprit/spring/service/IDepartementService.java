package tn.esprit.spring.service;


import java.util.List;


import tn.esprit.spring.entity.Departement;


public interface IDepartementService {
	
	
	public String addDepartement(Departement d) throws Exception;
	
	public String deleteDepartement(long id) throws Exception;
	public String updateDepartement(Departement d, long id) throws Exception;
	public List<Departement> getAllDepartements();

}
