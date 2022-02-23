package tn.esprit.spring.entity;


import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity

public class Publication  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@OneToMany(mappedBy="Publication", 
			cascade={CascadeType.PERSIST, CascadeType.REMOVE},
			fetch=FetchType.EAGER)
	private List<Commentaire> commentaire;
	
	@ManyToOne
	@JsonIgnore
	private Departement departement;
	
	
	
	
	
	
	

	
}
