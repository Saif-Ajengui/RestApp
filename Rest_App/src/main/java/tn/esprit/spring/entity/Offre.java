package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Offre implements Serializable {
	
	public int getId() {
		return id;
	}
 

	public void setId(int id) {
		this.id = id;
	}


	public Date getDate_DebutOffre() {
		return Date_DebutOffre;
	}


	public void setDate_DebutOffre(Date date_DebutOffre) {
		Date_DebutOffre = date_DebutOffre;
	}


	public Date getDate_FinOffre() {
		return Date_FinOffre;
	}


	public void setDate_FinOffre(Date date_FinOffre) {
		Date_FinOffre = date_FinOffre;
	}


	public String getDescription() {
		return Description;
	}


	public void setDescription(String description) {
		Description = description;
	}


	public float getOffre_Value() {
		return Offre_Value;
	}


	public void setOffre_Value(float offre_Value) {
		Offre_Value = offre_Value;
	}


	public Partner getPartner() {
		return partner;
	}


	public void setPartner(Partner partner) {
		this.partner = partner;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Temporal(TemporalType.DATE)
	private Date Date_DebutOffre;

	@Temporal(TemporalType.DATE)
	private Date Date_FinOffre;
	
	private String Description;
	
	private float Offre_Value;
	
	
	//Att de mapping:

	@ManyToOne
	@JsonIgnore
	private Partner partner;

	

}
