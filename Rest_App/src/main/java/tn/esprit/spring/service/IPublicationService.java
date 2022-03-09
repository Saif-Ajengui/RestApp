package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.Comment;
import tn.esprit.spring.entity.Publication;



public interface IPublicationService {
	
	
public String addPublication(Publication p,int idu,long idd) throws Exception;
	
	public String deletePublication(int id) throws Exception;
	
	public String updatePublication(Publication p, int id) throws Exception;
	
	public List<Publication> getAllPublication();
	public List<Publication> getPublicationByUserId(int id);
	public List<Publication> getCommentairesByDepartementId(long id);

}
