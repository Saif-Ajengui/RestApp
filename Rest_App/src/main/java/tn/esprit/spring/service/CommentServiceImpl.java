package tn.esprit.spring.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Comment;
import tn.esprit.spring.entity.Publication;
import tn.esprit.spring.entity.UnhealthyWord;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.ICommentRepository;
import tn.esprit.spring.repository.IPublicationRepository;
import tn.esprit.spring.repository.IUnhealthyWordRepository;
import tn.esprit.spring.repository.UserRepository;





@Service
public class CommentServiceImpl implements ICommentService {


	@Autowired 
	IUnhealthyWordRepository unhealthyWordRepository;
	@Autowired 
	ICommentRepository CommentaireRepository;
	@Autowired 
	IPublicationRepository PublicationRepository;
	@Autowired 
	UserRepository iUserRepository;
	
	@Override
	public String addCommentaire(Comment c, int idP,int idu) throws Exception {
		Publication publication=PublicationRepository.findById(idP).get();
		User user=iUserRepository.findById(idu).get();
		c.setUser(user);
		c.setPublication(publication);
		c.setCreateDate(LocalDateTime.now());
		//comment text approval
		for(UnhealthyWord uwd : unhealthyWordRepository.findAll()) {
			if(c.getCommentContent().toLowerCase().contains(uwd.getWord()))
				return("Sorry, you can't add comments that contain hate speech or bad words .");
		}
		
			CommentaireRepository.save(c);
		return ("commentaire ajouter"); 					
	}
		
	

	@Override
	public String deleteCommentaire(int id) throws Exception {
	  Comment c= CommentaireRepository.findById(id).get();
	  User u= iUserRepository.findById(c.getUser().getId()).get();
	 
		  
		  CommentaireRepository.deleteById(id);
		  return "commentaire supprimer";
		  
		  
		  
		
	  
		
	}

	@Override
	public String updateCommentaire(Comment c, int id, int idu) throws Exception {
		User user=c.getUser();
		
			
			c.setModifyDate(LocalDateTime.now());
			for(UnhealthyWord uwd : unhealthyWordRepository.findAll()) {
				if(c.getCommentContent().toLowerCase().contains(uwd.getWord())){
					return("Sorry, you can't comment hate speech or bad words .");
				}
			CommentaireRepository.save(c);
			return ("Comment updated successfully");	
		}
				return "++";
	}

	@Override
	public List<Comment> getAllCommentaires() {
		List<Comment>Comments = new ArrayList<Comment>();
		CommentaireRepository.findAll().forEach(e ->Comments.add(e));
		return Comments;
		
	}

	@Override
	public Comment getCommentaireById(int id) {
		
		return CommentaireRepository.findById(id).get();
		
		
		
	}

	@Override
	public int CountCommentaires() {
		List <Comment> comments=(List<Comment>) CommentaireRepository.findAll();
		return comments.size();
		
	}

	@Override
	public List<Comment> getCommentairesByPublicationId(int id) {
		return	CommentaireRepository.getCommentsByPostId(id);
		
	}

	@Override
	public int CountCommentairesByPublication(int id) {
		List <Comment> comments=(List<Comment>) CommentaireRepository. getCommentsByPostId(id);
		return comments.size();
	}

	@Override
	public List<Comment> searchCommentaires(String text) {
		return CommentaireRepository.findCommentsByTextContaining(text);
	}

	

	
	}