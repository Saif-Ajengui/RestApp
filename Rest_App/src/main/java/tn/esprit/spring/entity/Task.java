package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.Calendar;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Task implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private int id;
	
	private String project;
	
	private String title;
	
	private String description;

	@FutureOrPresent(message="date has to be future or present")
	@Temporal(TemporalType.DATE)
	private Calendar ddl;
	
	@Positive(message="the number of work days valued has to be positive")
	private int nbWorkDaysValued;
	
	@Enumerated(EnumType.STRING)
	private TaskState state;
	
	private boolean acceptAfterDDL;
	
	@Temporal(TemporalType.DATE)
	private Calendar startDate;
	
	@Temporal(TemporalType.DATE)
	private Calendar finishDate;	
	
	
	//attributs de mapping:
	
	@JsonIgnore
	@ManyToMany
	private List<User> users;
	
	
	
	@ManyToOne
	private Calendarr Calendarr;


	
	
	
	

}
