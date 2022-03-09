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


import tn.esprit.spring.entity.Comment;
import tn.esprit.spring.service.CommentServiceImpl;





@RestController
public class CommentController {
	
	@Autowired  
	CommentServiceImpl commentServiceImpl;
	
	//URL: http://localhost:8082/examen/Commentaire/add-commentaire/{idP}/{idu}
	@PostMapping("/Commentaire/add-commentaire/{idP}/{idu}")
	public String addCommentaire(@RequestBody Comment commentaires, @PathVariable("idP")int idP, @PathVariable("idu")int idu ) throws Exception   
	{  
		return (commentServiceImpl.addCommentaire(commentaires, idP,idu));  						
	} 
	
	@DeleteMapping("/Commentaire/delete-commentaire/{id}")  
	public void deleteCommentaire(@PathVariable("id") int id) throws Exception   
	{  
		commentServiceImpl.deleteCommentaire(id);  
	} 
	
	@PutMapping("/Commentaire/update-commentaire/{id}/{idu}")  
	public String updateCommentaire(@RequestBody Comment comments, @PathVariable("id")int id, @PathVariable("idu")int idu) throws Exception   
	{  
		return commentServiceImpl.updateCommentaire(comments,id,idu);  			  
	}
	
	@GetMapping("/Commentaire/get-all-commentaires")  
	public List<Comment> getAllComments()   
	{  
		return commentServiceImpl.getAllCommentaires();  
	}  
	@GetMapping("/Commentaire/comments-by-post/{idP}")
	public List<Comment> CountCommentairesByPublication(@PathVariable("idP") int idP) {
		return commentServiceImpl.getCommentairesByPublicationId(idP);

	}
	@GetMapping("/Commentaire/count-post-commentaires/{idP}")
	public int getpostcommentscount(@PathVariable("idP") int idP) {
		return commentServiceImpl.CountCommentairesByPublication(idP);
	}
	@GetMapping("/Commentaire/search/{pattern}")
	public List<Comment> commentSearch(@RequestParam("pattern")String pattern){
		//System.out.println(pattern);
		return commentServiceImpl.searchCommentaires(pattern);
	
	}
			

}
