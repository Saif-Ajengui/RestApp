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
@Getter 
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

}
