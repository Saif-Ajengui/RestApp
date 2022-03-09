package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Comment;
import tn.esprit.spring.entity.Publication;
import tn.esprit.spring.service.PublicationServiceImpl;


@RestController
public class PublicationController {
	
	@Autowired  
	PublicationServiceImpl publicationServiceImpl;
	
	//URL: http://localhost:8082/examen/Publication/add-post/{idu}/{idd}
			
				@PostMapping("/Publication/add-post/{idu}/{idd}")  
				public String addPublication(@RequestBody Publication Posts,@PathVariable("idu")int idu,@PathVariable("idd")long idd) throws Exception   
				{  
					return  (publicationServiceImpl.addPublication(Posts,idu,idd));  
				} 
				
				
				//URL: http://localhost:8082/examen/Publication/delete-publication/{id}
				@DeleteMapping("/Publication/delete-publication/{id}")  
				public String deletePublication(@PathVariable("id") int id) throws Exception   
				{  
					return (publicationServiceImpl.deletePublication(id));  
				} 		

				//URL: http://localhost:8082/examen/Publication/get-all-post/{id}
				@GetMapping("/Publication/get-all-post")  
				public List<Publication> getAllPosts()   
				{  
					return publicationServiceImpl.getAllPublication();  
				}  
				
				@PutMapping("/Publication/update-publication/{id}")  
				public String updatePublication(@RequestBody Publication posts, @PathVariable("id")int id) throws Exception   
				{  
					return publicationServiceImpl.updatePublication(posts,id);   
				}
				@GetMapping("/Publication/Publication-by-user/{id}")
				public List<Publication> getCommentairesByUserId(@PathVariable("id") int id) {
					return publicationServiceImpl.getPublicationByUserId(id);

				}
				@GetMapping("/Publication/Publication-by-Departement/{id}")
				public List<Publication> getCommentairesByDepartementId(@PathVariable("id") long id) {
					return publicationServiceImpl.getCommentairesByDepartementId(id);

				}
}
