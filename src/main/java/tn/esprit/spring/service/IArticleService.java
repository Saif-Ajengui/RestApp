package tn.esprit.spring.service;

import java.io.IOException;
import java.util.List;

import tn.esprit.spring.entity.Article;


public interface IArticleService {
	
	List<Article> retrieveAllArticle();
	Article addArticle(Article c);
	void deleteArticle(Long id);
	Article updateArticle(Article c);
	Article retrieveArticle(Long id);
	List<Integer> relatedArticles(long id) throws IOException;
	void affecterArtSubj(long idVm, long idsub);
	
}
