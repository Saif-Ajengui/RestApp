package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import tn.esprit.spring.entity.Article;
import tn.esprit.spring.entity.Question;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class User implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer id;
	
	
	private String lname;

	private String fname;
	
	private String pic;

	private String email;
	
	private String pwd;
	
	private boolean approved;
	

	@Enumerated(EnumType.STRING)
	private Role type;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
	private Set<Notification> notif;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "sender", fetch = FetchType.EAGER)
	private Set<TChat> TChatS;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "receiver", fetch = FetchType.EAGER)
	private Set<TChat> TChatR;

	@OneToMany(cascade = CascadeType.ALL, mappedBy="U_Articles")
	private Set<Article> Articles;
		
	@ManyToMany(cascade = CascadeType.ALL) 
	private Set<Question> U_Question;
		

	
	
}
