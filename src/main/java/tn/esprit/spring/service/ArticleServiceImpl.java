package tn.esprit.spring.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import tn.esprit.spring.entity.Article;
import tn.esprit.spring.entity.Subject;
import tn.esprit.spring.repository.ArticleRepository;
import tn.esprit.spring.repository.SubjectRepository;
import tn.esprit.spring.service.IArticleService;


import java.util.stream.Collectors;
import java.util.stream.Stream;



@Service
public class ArticleServiceImpl implements IArticleService {
	
	@Autowired
	ArticleRepository ArticleRepository;
	
	@Autowired
	SubjectRepository SubjectRepository;
	

	
	public List<Article> retrieveAllArticle() {
		List<Article> articles = (List<Article>) ArticleRepository.findAll();
		return articles;
	}

	public Article addArticle(Article c) {
		//c.setContent(cleanBadWords(c.getContent()));
		return	ArticleRepository.save(c) ;
		
	}

	
	public void deleteArticle(Long id) {
		ArticleRepository.deleteById(id);
		
	}

	
	public Article updateArticle(Article c) {
		return ArticleRepository.save(c) ;
	}

	
	public Article retrieveArticle(Long id) {
		Article Article = ArticleRepository.findById(id).orElse(null) ;
		return Article;
	}
	
	 
	public void affecterArtSubj(long idVm, long idsub) {
		
		Article art = ArticleRepository.findById(idVm).orElse(null);

		Subject sub = SubjectRepository.findById(idsub).orElse(null);
		//Vms est la table associative entre les Vm et les users
		sub.getArticles().add(art);
		
		ArticleRepository.save(art);
	}
	
//clean Method--------------------------------------------------------------
	
	public String cleanContent(long idp) throws IOException {
		Article p = ArticleRepository.getById(idp);
		List<String> stopwords = Files.readAllLines(Paths.get("stop_words_english.txt"));

		ArrayList<String> allWords = Stream.of(p.getContent().toLowerCase().split(" "))
				.collect(Collectors.toCollection(ArrayList<String>::new));
		allWords.removeAll(stopwords);
//FROM LIST TO STRING
		return allWords.stream().collect(Collectors.joining(" "));
	}

//Related methods------------------------------------------------------------
	
	
	public int relatedContent(long idp, long irp) throws IOException {
		Article p =  ArticleRepository.getById(idp);
		Article rp =  ArticleRepository.getById(irp);
		int count = 0;
		//clean content heya l fonction li trajaalek l content taa l'article maghir les mots communs
		//de la langue Anglaise comme exemple (is, the, from...)
		String content = cleanContent(irp);
		String content2=  cleanContent(idp);
		//SEPARATE WORDS OF A STRING
		// wordsTitle howa l content taa l article lowel li l id teeou "idp"
		String[] wordsTitle = content2.split(" ");
		// wordsContent howa l content taa l article l theni li l id teeou "irp"
		String[] wordsContent = content.split(" ");
		for (int j = 0; j < wordsTitle.length; j++) {

			
			
				for (int i = 0; i < wordsContent.length; i++) {

					if (wordsTitle[j].equals(wordsContent[i])) {

						count++;
					}

				}
			}
		
		return count;
	}

//----------------------------------------------------------------------------------
	
	public List<Integer> relatedArticles(long ida) throws IOException {

		Article p = ArticleRepository.findById(ida).orElse(null);
		// stamalna Map avec 2 parametre ID TAA article et counter elli howa score taa similarité
		Map<Integer, Integer> relatedP = new HashMap<Integer, Integer>();
		System.out.println("Check 1");
		for (Article a : retrieveAllArticle()) {
			// bech nal9aw tous les articles sauf l'article elli ahna deja fih
			if (a.getIdArticle() != p.getIdArticle()) {
				
				int counter = 0;
				
				System.out.println("Check 2");
					counter = relatedContent(p.getIdArticle(), a.getIdArticle());
					if (counter >= 1) {
						relatedP.put((int) a.getIdArticle(), counter);
						System.out.println("POST1=" + a.getIdArticle());
						System.out.println("POST2=" + relatedP.get(a.getIdArticle()));
						System.out.println("Check last counter ="+ counter);
					}
					
				
			}
		}
		if (relatedP != null) {
			final List<Integer> sorted = relatedP.entrySet().stream()
					.sorted(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder())).map(Map.Entry::getKey)
					.collect(Collectors.toList());

			return sorted;
		}

		return null;
	}
	
	
	



	
}
