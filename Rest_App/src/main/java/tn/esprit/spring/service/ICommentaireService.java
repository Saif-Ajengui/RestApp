package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.Commentaire;



public interface ICommentaireService {
	
	public String addCommentaire(Commentaire c, int idP, int idu) throws Exception;
	public String deleteCommentaire(int id) throws Exception;
	public String updateCommentaire(Commentaire c, int id,int idu) throws Exception;
	public List<Commentaire> getAllCommentaires();
	public Commentaire getCommentaireById(int id);
	public int CountCommentaires();
	
	
	public List<Commentaire> getCommentairesByPublicationId(int id);
	public int CountCommentairesByPublication(int id);
    public List<Commentaire> searchCommentaires(String text);
    
}
