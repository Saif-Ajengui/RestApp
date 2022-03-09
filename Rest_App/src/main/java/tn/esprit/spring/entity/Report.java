package tn.esprit.spring.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;




@Entity
@Table(name= "Report")




public class Report implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name= "id")
	private int id;
	
	private String descritpion;
	

	@ManyToOne
	@JoinColumn(name= "id_pub")
	private Publication publication;
	
	
	public Report() {
		super();
	}

	public int getIdReport() {
		return id;
	}

	public void setReport(int id) {
		this.id = id;
	}
	
	public String Description() {
		return descritpion;
	}

	public void setdescritpion(String descritpion) {
		this.descritpion = descritpion;
	}
	public  Publication getPublication() {
		return publication;
	}

	public void setPublication( Publication  publication) {
	this.publication =  publication;
	}

}
