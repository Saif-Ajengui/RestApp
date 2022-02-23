package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity @Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private int id;
	
	private String title;
	
	private String description;
	
	@Temporal(TemporalType.DATE)
	private Date ddl;
	
	@Enumerated(EnumType.STRING)
	private TaskState state;
	
	private boolean acceptAfterDDL;
	
	private float scoreTask;
	
	
	//attributs de mapping:
	
	@ManyToMany(mappedBy="task", cascade= CascadeType.ALL)
	private Set<User> user;
	
	

}
