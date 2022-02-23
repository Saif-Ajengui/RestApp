package tn.esprit.spring.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

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
		
	public String imgName;
	
	public String imgPath;
	
	public int nbVoteYes;
	
	public int nbVoteNo;
	
	public int nbVoteABS;
	
	//attributs de mapping:
	@OneToOne(mappedBy="badge")
	private User user;

}
