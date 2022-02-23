package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
@Table(name= "event")
public class Event implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name= "id")
	private int idEvenement;
	@Column(name= "title")
	private String title;
	@Temporal(TemporalType.DATE)
	private Date date;
	@Column(name= "description")
	private String description;
	@Column(name= "placesNbr")
	private int placesNbr;
	@OneToMany(cascade= CascadeType.ALL, mappedBy= "event")
	
	private Set<Participation> participations;
	@Column(name= "image")
    @Lob
    private byte[] image;
	private int views;
	
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public int getIdEvenement() {
		return idEvenement;
	}
	public void setIdEvenement(int idEvenement) {
		this.idEvenement = idEvenement;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPlacesNbr() {
		return placesNbr;
	}
	public void setPlacesNbr(int placesNbr) {
		this.placesNbr = placesNbr;
	}
	public Set<Participation> getParticipations() {
		return participations;
	}
	public void setParticipations(Set<Participation> participations) {
		this.participations = participations;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Event(String title, Date date, String description, int placesNbr, Set<Participation> participations,
			byte[] image,int views) {
		super();
		this.title = title;
		this.date = date;
		this.description = description;
		this.placesNbr = placesNbr;
		this.participations = participations;
		this.image = image;
		this.views=views;
	}
	
}
