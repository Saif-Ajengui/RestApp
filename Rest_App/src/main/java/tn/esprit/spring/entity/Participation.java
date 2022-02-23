package tn.esprit.spring.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "participation")
public class Participation implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	@EmbeddedId
	private ParticipationPK participationPK;
	
	

	@ManyToOne
	@JoinColumn(name = "idEvent", referencedColumnName = "id", insertable = false, updatable = false)
	private Event event;

	public ParticipationPK getParticipationPK() {
		return participationPK;
	}

	public void setParticipationPK(ParticipationPK participationPK) {
		this.participationPK = participationPK;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Participation(ParticipationPK participationPK, Event event) {
		super();
		this.participationPK = participationPK;
		this.event = event;
	}
	


}
