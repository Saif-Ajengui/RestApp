package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String project;

	private String title;

	private String description;

	@FutureOrPresent(message = "date has to be future or present")
	@Temporal(TemporalType.DATE)
	private Calendar ddl;

	@Positive(message = "the number of work days valued has to be positive")
	private int nbWorkDaysValued;

	@Enumerated(EnumType.STRING)
	private TaskState state;

	private boolean acceptAfterDDL;

	// TimeZone z1 = TimeZone.getTimeZone("GMT");
	// @Temporal(TemporalType.TIME)
	private Calendar createdAt = Calendar.getInstance(TimeZone.getTimeZone("GMT+01:00"));

	@Temporal(TemporalType.DATE)
	private Calendar startDateConfirm;

	@Temporal(TemporalType.DATE)
	private Calendar finishDateConfirm;

	@Temporal(TemporalType.DATE)
	private Calendar startDate;

	@Temporal(TemporalType.DATE)
	private Calendar finishDate;

	// attributs de mapping:
	@JsonIgnore
	@ManyToMany
	private List<User> users;

	
	// attributs de mapping:
	@OneToMany(mappedBy="task", 
			cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	@JsonIgnore
	private List<Deposit> deposits = new ArrayList<>();

	@Override
	public String toString() {
		return " * Task \n[project name=" + project 
				+ ", \nTask title=" + title 
				+ ", \ndescription=" + description
				+ ", \ndeadline=" + ddl.get(Calendar.YEAR) + "-" + ddl.get(Calendar.MONTH) + "-"+ ddl.get(Calendar.DAY_OF_MONTH)
				+ ", \nNumber of Work Days Planned=" + nbWorkDaysValued + ", \nTask state="
				+ state + ", \nAccept After deadline=" + acceptAfterDDL 
				+ ", \nTask created at="+ createdAt.get(Calendar.YEAR) + "-" + createdAt.get(Calendar.MONTH) + "-"+ createdAt.get(Calendar.DAY_OF_MONTH) 
				+ ", \nStart Date=" + startDateConfirm.get(Calendar.YEAR)+ "-" + startDateConfirm.get(Calendar.MONTH) + "-" + startDateConfirm.get(Calendar.DAY_OF_MONTH)
				+ ", \nfinish Date=" + finishDateConfirm.get(Calendar.YEAR) + "-"+ finishDateConfirm.get(Calendar.MONTH) + "-" + finishDateConfirm.get(Calendar.DAY_OF_MONTH) 
				+ "]";
	}

}
