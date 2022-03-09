package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;



@Getter
@Setter
@ToString
@RequiredArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Subject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idSubject;
	private String nom;
	private int nb_article;
	//mapping
	@OneToMany(cascade = CascadeType.ALL, mappedBy="subjects")
	private Set<Article> Articles;
	public long getIdSubject() {
		return idSubject;
	}
	public void setIdSubject(long idSubject) {
		this.idSubject = idSubject;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getNb_article() {
		return nb_article;
	}
	public void setNb_article(int nb_article) {
		this.nb_article = nb_article;
	}
	public Set<Article> getArticles() {
		return Articles;
	}
	public void setArticles(Set<Article> articles) {
		Articles = articles;
	}

	


	
}
