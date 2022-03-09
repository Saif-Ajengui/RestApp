package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.Comment;



public interface ICommentService {
	
	public String addCommentaire(Comment c, int idP, int idu) throws Exception;
	public String deleteCommentaire(int id) throws Exception;
	public String updateCommentaire(Comment c, int id,int idu) throws Exception;
	public List<Comment> getAllCommentaires();
	public Comment getCommentaireById(int id);
	public int CountCommentaires();
	
	
	public List<Comment> getCommentairesByPublicationId(int id);
	public int CountCommentairesByPublication(int id);
    public List<Comment> searchCommentaires(String text);
    
}
