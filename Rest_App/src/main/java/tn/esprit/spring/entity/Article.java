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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.sun.istack.NotNull;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;



@Getter
@Setter
@ToString
@RequiredArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Article implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idArticle;
	@NotNull
	private String content;
	@Temporal (TemporalType.DATE)
	private Date date;
	//mapping
	@ManyToOne
	User U_Articles;
	@ManyToOne
	Subject subjects;
	public long getIdArticle() {
		return idArticle;
	}
	public void setIdArticle(long idArticle) {
		this.idArticle = idArticle;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public User getU_Articles() {
		return U_Articles;
	}
	public void setU_Articles(User u_Articles) {
		U_Articles = u_Articles;
	}
	public Subject getSubjects() {
		return subjects;
	}
	public void setSubjects(Subject subjects) {
		this.subjects = subjects;
	}
	public Article(long idArticle, String content, Date date, User u_Articles, Subject subjects) {
		super();
		this.idArticle = idArticle;
		this.content = content;
		this.date = date;
		U_Articles = u_Articles;
		this.subjects = subjects;
	}
	public Article() {
		super();
	} 

	


	
}
