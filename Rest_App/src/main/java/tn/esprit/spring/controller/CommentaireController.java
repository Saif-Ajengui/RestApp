package tn.esprit.spring.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Commentaire;
import tn.esprit.spring.service.CommentaireServiceImpl;

@RestController
public class CommentaireController {
	
		@Autowired  
		CommentaireServiceImpl commentServiceImpl;
		
				@PostMapping("/comment/add-comment/{ide}")  
				public Commentaire addComment(@RequestBody Commentaire comments, @PathVariable("ide")int ide) throws Exception   
				{  
					return (commentServiceImpl.addCommentaire(comments, ide));  						
				} 
				

				@DeleteMapping("/comment/delete-comment/{Commentid}")  
				public void deleteComment(@PathVariable("Commentid") int Commentid) throws Exception   
				{  
					commentServiceImpl.deleteCommentaire(Commentid);  
				} 


				@PutMapping("/comment/update-comment/{Commentid}")  
				public String updateComment(@RequestBody Commentaire comments, @PathVariable("Commentid")int Commentid) throws Exception   
				{  
					return commentServiceImpl.updateCommentaire(comments,Commentid);  			  
				}
				
				
				@GetMapping("/comment/get-all-comments")  
				public List<Commentaire> getAllComments()   
				{  
					return commentServiceImpl.getAllCommentaires();  
				}  
		
				
			@GetMapping("/comment/get-my-comments")  
			public List<Commentaire> getMyComments() throws Exception   
			{  
				return commentServiceImpl.getMyCommentaires();  
			}
		
		
				@GetMapping("/comment/detail-comment/{Commentid}")  
				public Commentaire getComment(@PathVariable("Commentid") int Commentid)   
				{  
					return commentServiceImpl.getCommentaireById(Commentid);  
				}  
				
				
			
				
				@GetMapping("/comment/count-all-comments")
				public int getcommentscount() {
					return commentServiceImpl.CountComments();
				}
		
			
				
				@GetMapping("/comment/comments-by-post/{idE}")
				public List<Commentaire> getCommentsByPost(@PathVariable("idE") int idE) {
					return commentServiceImpl.getCommentairesByEventId(idE);

				}
				
			
				
				//METIER
				
				@GetMapping("/comment/search/")
				public List<Commentaire> commentSearch(@RequestParam("pattern")String pattern){
					System.out.println(pattern);
					return commentServiceImpl.searchCommentaire(pattern);
				
				}
						
				
	}