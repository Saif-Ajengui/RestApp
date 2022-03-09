package tn.esprit.spring.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name= "rating")
public class Rating implements Serializable{

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int idEvalution ;
	
	private int rate;
	
	
	@ManyToOne
	private  User user;
	
	@ManyToOne
	private Event event;
	
	
	public   Rating() {
		
	}


	public  Rating(int idEvalution, int rate, User user, Event event) {
		super();
		this.idEvalution = idEvalution;
		this.rate = rate;
		this.user = user;
		this.event = event;
	}


	public int getIdEvalution() {
		return idEvalution;
	}


	public void setIdEvalution(int idEvalution) {
		this.idEvalution = idEvalution;
	}


	

	

	public int getRate() {
		return rate;
	}


	public void setRate(int rate) {
		this.rate = rate;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Event getEvent() {
		return event;
	}


	public void setEvent(Event event) {
		this.event = event;
	}


}
