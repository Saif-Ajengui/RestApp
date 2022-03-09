package tn.esprit.spring.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.events.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import tn.esprit.spring.entity.Commentaire;
import tn.esprit.spring.entity.Publication;
import tn.esprit.spring.entity.UnhealthyWord;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.ICommentaireRepository;
import tn.esprit.spring.repository.IPublicationRepository;
import tn.esprit.spring.repository.IUnhealthyWordRepository;
import tn.esprit.spring.repository.IUserRepository;

@Service
public class CommentaireServiceImpl implements ICommentaireService {

	
	@Autowired 
	IUnhealthyWordRepository unhealthyWordRepository;
	@Autowired 
	ICommentaireRepository CommentaireRepository;
	@Autowired 
	IPublicationRepository PublicationRepository;
	@Autowired 
	IUserRepository UserRepository;
	
	@Override
	public String addCommentaire(Commentaire c, int idP,int idu) throws Exception {
		Publication publication=PublicationRepository.findById(idP).get();
		User user=UserRepository.findById(idu).get();
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
	  Commentaire c= CommentaireRepository.findById(id).get();
	  User u= UserRepository.findById(c.getUser().getIdUser()).get();
	 
		  
		  CommentaireRepository.deleteById(id);
		  return "commentaire supprimer";
		  
		  
		  
		
	  
		
	}

	@Override
	public String updateCommentaire(Commentaire c, int id, int idu) throws Exception {
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
	public List<Commentaire> getAllCommentaires() {
		List<Commentaire>Comments = new ArrayList<Commentaire>();
		CommentaireRepository.findAll().forEach(e ->Comments.add(e));
		return Comments;
		
	}

	@Override
	public Commentaire getCommentaireById(int id) {
		
		return CommentaireRepository.findById(id).get();
		
		
		
	}

	@Override
	public int CountCommentaires() {
		List <Commentaire> comments=(List<Commentaire>) CommentaireRepository.findAll();
		return comments.size();
		
	}

	@Override
	public List<Commentaire> getCommentairesByPublicationId(int id) {
		return	CommentaireRepository.getCommentsByPostId(id);
		
	}

	@Override
	public int CountCommentairesByPublication(int id) {
		List <Commentaire> comments=(List<Commentaire>) CommentaireRepository. getCommentsByPostId(id);
		return comments.size();
	}

	@Override
	public List<Commentaire> searchCommentaires(String text) {
		return CommentaireRepository.findCommentsByTextContaining(text);
	}

	
	}
	
	

		
	


