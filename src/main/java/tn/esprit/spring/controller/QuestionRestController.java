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

@RequestMapping("/Ques")
public class QuestionRestController {

	//@Autowired
	//IArticleService ArticleService;

	@Autowired
	IQuestionService QuestionService;
/*	
	@Autowired
	ISubjectService SubjectService;
	*/ 
	 // http://localhost:8084/SpringMVC/form/add-art
	/* @PostMapping("/addart")
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
		 
	/**/	 
//---------------------------------------------------------------
		 
		 @PostMapping("/addQuest")
		 @ResponseBody
		 public Question ajouterQuestion(@RequestBody Question s) throws IOException
		{
			 return QuestionService.addQuestion(s);
				
		}
		 
		 @GetMapping("/getQuest")
			public List<Question> getQuest(){
				return QuestionService.retrieveAllQuestion();
			}
		 
		 @PutMapping("/modify-Quest")
		 @ResponseBody	 
		 public Question modifyQuestion(@RequestBody Question Question) {
		 return QuestionService.updateQuestion(Question);
		 }
		 
		 @DeleteMapping("/remove-Quest/{Question-id}")
		 @ResponseBody
		 public void removeQuestion(@PathVariable("Question-id") Long QuestionId) {
			 QuestionService.deleteQuestion(QuestionId);
		 }
//-------------------------------------------------------------
		/*
		 @PostMapping("/add-Sub")
		 @ResponseBody
		 public void ajouterSubject(@RequestBody Subject s)
		{
			 SubjectService.addSubject(s);
				
		}
		 
		 @GetMapping("/getSubject")
			public List<Subject> getSubject(){
				return SubjectService.retrieveAllSubject();
			}
		 
		 @PutMapping("/modify-Subject")
		 @ResponseBody	 
		 public Subject modifySubject(@RequestBody Subject Subject) {
		 return SubjectService.updateSubject(Subject);
		 }
		 
		 @DeleteMapping("/remove-Subject/{Subject-id}")
		 @ResponseBody
		 public void removeSubject(@PathVariable("Subject-id") Long SubjectId) {
			 SubjectService.deleteSubject(SubjectId);
		 }
		 
		 /*
		 @GetMapping("/clean/{idp}")
			@ResponseBody
		    public String clean(@PathVariable("idp") int idp) throws IOException {
		     return ArticleService.cleanContent(idp);
		    }
	*/	 
}
