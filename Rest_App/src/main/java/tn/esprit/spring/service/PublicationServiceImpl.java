package tn.esprit.spring.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Departement;
import tn.esprit.spring.entity.Publication;
import tn.esprit.spring.entity.UnhealthyWord;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.IDepartementRepository;
import tn.esprit.spring.repository.IPublicationRepository;
import tn.esprit.spring.repository.IUnhealthyWordRepository;
import tn.esprit.spring.repository.UserRepository;






@Service
public class PublicationServiceImpl implements IPublicationService {
	
	@Autowired 
	IPublicationRepository iPublicationRepository;
	@Autowired 
	IUnhealthyWordRepository unhealthyWordRepository;
	
	@Autowired 
	UserRepository iUserRepository;
	@Autowired 
	IDepartementRepository DepartementRepository;
	
	@Override
	public String addPublication(Publication p, int idu, long idd) throws Exception {
		User user=iUserRepository.findById(idu).get();
		Departement Departement = DepartementRepository.findById(idd).get();
		p.setCreateDate(LocalDateTime.now());
		p.setUser(user);
		p.setDepartement(Departement);
		for(UnhealthyWord uwd : unhealthyWordRepository.findAll()) {
			
				if(p.getPostContent().toLowerCase().contains(uwd.getWord())){
					
					return("Sorry, you can't add comments that contain hate speech or bad words .");
				}}
		iPublicationRepository.save(p)	;
		return ("Publication Ajouter ");
	}

	@Override
	public String deletePublication(int id) throws Exception {
		Publication p = iPublicationRepository.findById(id).get();
			User u= iUserRepository.findById(p.getUser().getId()).get();
			
			
			
				iPublicationRepository.delete(p);
				return "Post deleted successfully";
			
		}
		
	 

	@Override
	public String updatePublication(Publication p, int id) throws Exception {
		Publication pub = iPublicationRepository.findById((int) id).get();
		User c =iUserRepository.findById(pub.getUser().getId()).get();
		if (!(c.getId()==pub.getUser().getId()) ){
			return("You are not allowed to update this post");}
		else {
			for(UnhealthyWord uwd : unhealthyWordRepository.findAll()) {
				if(p.getPostContent().toLowerCase().contains(uwd.getWord())){
					
					return"Sorry, you can't post hate speech or bad words .";
				}
			
			
		
			
				else{
					p.setModifyDate(LocalDateTime.now());
					p.setPublicationtContent(p.getPostContent());
					
					
					iPublicationRepository.save(p);}
					return ("Post updated successfully");
				
		
	}}
		return "";}
			
		
	
		

	@Override
	public List<Publication> getAllPublication() {
		
			List<Publication>Posts = new ArrayList<Publication>();
			iPublicationRepository.findAll().forEach(e ->Posts.add(e));
			return Posts;
		

		
	}

	@Override
	public List<Publication> getPublicationByUserId(int id) {
		return	iPublicationRepository.findPostsByUserid(id);
		}

	@Override
	public List<Publication> getCommentairesByDepartementId(long id) {
		return iPublicationRepository.findPostsByDepartementid(id);
		
	}

}
