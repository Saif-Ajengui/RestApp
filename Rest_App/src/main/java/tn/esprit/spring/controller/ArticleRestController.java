package tn.esprit.spring.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Article;
import tn.esprit.spring.entity.Question;
import tn.esprit.spring.entity.Subject;
import tn.esprit.spring.service.ArticleServiceImpl;
import tn.esprit.spring.service.IArticleService;
import tn.esprit.spring.service.IQuestionService;
import tn.esprit.spring.service.ISubjectService;


@RestController

@RequestMapping("/form")
public class ArticleRestController {

	@Autowired
	IArticleService ArticleService;
/*	
	@Autowired
	IQuestionService QuestionService;
	
	@Autowired
	ISubjectService SubjectService;
	*/ 
	 // http://localhost:8084/SpringMVC/form/add-art
	 @PostMapping("/addart")
	 @ResponseBody
	 public void ajouterArticle(@RequestBody Article s)
	{
		 ArticleService.addArticle(s);
			
	}
	 
	 @GetMapping("/getArt")
		public List<Article> getArticle(){
			return ArticleService.retrieveAllArticle();
		}
	 
	 @DeleteMapping("/remove-art/{aricle-id}")
	 @ResponseBody
	 public void removeclient(@PathVariable("article-id") Long articleId) {
		 ArticleService.deleteArticle(articleId);
	 }
	 
	// http://localhost:8084/SpringMVC/client/modify-client
		 @PutMapping("/modify-art")
		 @ResponseBody	 
		 public Article modifyClient(@RequestBody Article article) {
		 return ArticleService.updateArticle(article);
		 }
		 
	
		 
		 @PutMapping("/affecter-art-sub/{idart}/{idsub}")
			public void affecterartsub(@PathVariable("idart") long idart, @PathVariable("idsub") long idsub){
				ArticleService.affecterArtSubj(idart, idsub);
			}
		 
		 @GetMapping("/special/{id}")
			public List<Integer> relatedArticles(@PathVariable("id") int idart) throws IOException{
				return ArticleService.relatedArticles(idart);
			}
		 
	 
}
