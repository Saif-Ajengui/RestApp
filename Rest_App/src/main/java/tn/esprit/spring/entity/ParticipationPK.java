package tn.esprit.spring.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable

public class ParticipationPK implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int idEvent;
	public ParticipationPK() {
		super();
	}
	public ParticipationPK( int idEvent) {
		super();
	
		this.idEvent = idEvent;
		
	}
	public int getIdEvent() {
		return idEvent;
	}
	public void setIdEvent(int idEvent) {
		this.idEvent = idEvent;
	}
	

}
