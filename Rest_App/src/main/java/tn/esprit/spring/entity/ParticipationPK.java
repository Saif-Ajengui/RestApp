package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable

public class ParticipationPK implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private int idUser;
	private int idEvent;
	
	
	public ParticipationPK() {
		super();
	}
	public ParticipationPK(int idUser, int idEvent) {
		super();
		this.idUser = idUser;
		this.idEvent = idEvent;
		
	}
	
	
	
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public int getIdEvent() {
		return idEvent;
	}
	public void setIdEvent(int idEvent) {
		this.idEvent = idEvent;
	}
	
	
}