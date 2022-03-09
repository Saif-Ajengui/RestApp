package tn.esprit.spring.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.validation.constraints.PositiveOrZero;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 

public class Badge implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private int id;
		
		
		//@Digits(value = 3000, fraction = 0, integer = 0)
	    @Lob
		@Column(unique = false, nullable = false, length = 100000)
		public byte[] img;	

		@Column(nullable = true)
		@PositiveOrZero(message="fiel has to be greater than or equal to 0")
		public int nbVoteYes;
		
		@Column(nullable = true)
		@PositiveOrZero(message="fiel has to be greater than or equal to 0")
		public int nbVoteNo;
		
		@Column(nullable = true)
		@PositiveOrZero(message="fiel has to be greater than or equal to 0")
		public int nbVoteABS;
	
	//attributs de mapping:
	@OneToOne(mappedBy="badge")
	private User user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte[] getImg() {
		return img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}

	public int getNbVoteYes() {
		return nbVoteYes;
	}

	public void setNbVoteYes(int nbVoteYes) {
		this.nbVoteYes = nbVoteYes;
	}

	public int getNbVoteNo() {
		return nbVoteNo;
	}

	public void setNbVoteNo(int nbVoteNo) {
		this.nbVoteNo = nbVoteNo;
	}

	public int getNbVoteABS() {
		return nbVoteABS;
	}

	public void setNbVoteABS(int nbVoteABS) {
		this.nbVoteABS = nbVoteABS;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Badge(int id, byte[] img,
			@PositiveOrZero(message = "fiel has to be greater than or equal to 0") int nbVoteYes,
			@PositiveOrZero(message = "fiel has to be greater than or equal to 0") int nbVoteNo,
			@PositiveOrZero(message = "fiel has to be greater than or equal to 0") int nbVoteABS, User user) {
		super();
		this.id = id;
		this.img = img;
		this.nbVoteYes = nbVoteYes;
		this.nbVoteNo = nbVoteNo;
		this.nbVoteABS = nbVoteABS;
		this.user = user;
	}

	public Badge() {
		super();
	}

	public Badge(int id, byte[] img,
			@PositiveOrZero(message = "fiel has to be greater than or equal to 0") int nbVoteYes,
			@PositiveOrZero(message = "fiel has to be greater than or equal to 0") int nbVoteNo,
			@PositiveOrZero(message = "fiel has to be greater than or equal to 0") int nbVoteABS) {
		super();
		this.id = id;
		this.img = img;
		this.nbVoteYes = nbVoteYes;
		this.nbVoteNo = nbVoteNo;
		this.nbVoteABS = nbVoteABS;
	}
	

}
