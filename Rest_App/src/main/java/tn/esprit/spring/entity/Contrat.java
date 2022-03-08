package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Contrat implements Serializable  {
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate_Debut() {
		return Date_Debut;
	}

	public void setDate_Debut(Date date_Debut) {
		Date_Debut = date_Debut;
	}

	public Date getDate_Fin() {
		return Date_Fin;
	}

	public void setDate_Fin(Date date_Fin) {
		Date_Fin = date_Fin;
	}

	public float getContract_value() {
		return contract_value;
	}

	public void setContract_value(float contract_value) {
		this.contract_value = contract_value;
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
	private Date Date_Debut;

	@Temporal(TemporalType.DATE)
	private Date Date_Fin;
	
	private float contract_value;
	
	//Att de mapping:
	
	@OneToOne (mappedBy="contrat")
	
	private Partner partner;
}
