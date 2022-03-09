package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Article;


public interface ArticleRepository extends JpaRepository<Article,Long> {
	

	
	//List<Facture> getFacturesByClient(Long id);
	

	
}
