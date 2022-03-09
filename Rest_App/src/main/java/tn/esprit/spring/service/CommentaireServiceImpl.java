package tn.esprit.spring.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Commentaire;
import tn.esprit.spring.repository.CommentaireRepository;
import tn.esprit.spring.repository.EventRepository;
import tn.esprit.spring.repository.UserRepository;

@Service
public class CommentaireServiceImpl implements ICommentaireService{

	@Autowired 
	CommentaireRepository iCommentaireRepository;
	
	@Autowired
	UserRepository iUserRepo;
	
	@Autowired
	EventRepository iEventRepo;
	

	
	@Override
	public Commentaire addCommentaire(Commentaire c, int ide) throws Exception {
		c.setUser(iUserRepo.findById(1).get());
		if(iEventRepo.findById(ide).get()!=null) {
			c.setEvent(iEventRepo.findById(ide).get());

		}
		c.setCreateDate(LocalDateTime.now());
		
		
		iCommentaireRepository.save(c);
		
		return c;
	}

	
	
	@Override
	public String deleteCommentaire(int id) throws Exception {
		Commentaire c = iCommentaireRepository.findById(id).get();
			iCommentaireRepository.deleteById(id);
		return ("Comment deleted successfully");
		}
		
	

	@Override
	public String updateCommentaire(Commentaire c, int id) throws Exception {
		Commentaire comment = iCommentaireRepository.findById(id).get();
		//only the owner of the comment or the admin can update it
		
		comment.setModifyDate(LocalDateTime.now());
		
		iCommentaireRepository.save(comment);
		return ("Comment updated successfully");	
	}


	@Override
	public List<Commentaire> getAllCommentaires() {
		List<Commentaire>Comments = new ArrayList<Commentaire>();
		iCommentaireRepository.findAll().forEach(e ->Comments.add(e));
		return Comments;
	}

	
	@Override
	public List<Commentaire> getMyCommentaires() throws Exception {
		return iCommentaireRepository.getCommentsByUserId(1);
	}
	
	
	@Override
	public Commentaire getCommentaireById(int id) {
		return iCommentaireRepository.findById(id).get();  
	}




	
	@Override
	public List<Commentaire> getCommentairesByEventId(int id) {
		return iCommentaireRepository.getCommentsByEventId(id);
	}

	
	@Override
	public int CountComments() {
		List <Commentaire> comments=(List<Commentaire>) iCommentaireRepository.findAll();
		return comments.size();
	}
	

	@Override
	public List<Commentaire> searchCommentaire(String pattern) {
        return iCommentaireRepository.findCommentsByTextContaining(pattern);
	}








}