package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.Commentaire;
import tn.esprit.spring.entity.User;

public interface ICommentaireService {

	public Commentaire addCommentaire(Commentaire c, int idP) throws Exception;
	public String deleteCommentaire(int id) throws Exception;
	public String updateCommentaire(Commentaire c, int id) throws Exception;
	public List<Commentaire> getAllCommentaires();
	public Commentaire getCommentaireById(int id);
	public int CountComments();
	public List<Commentaire> getCommentairesByEventId(int id);

    public List<Commentaire> searchCommentaire(String text);
	public List<Commentaire> getMyCommentaires() throws Exception;
	
}
