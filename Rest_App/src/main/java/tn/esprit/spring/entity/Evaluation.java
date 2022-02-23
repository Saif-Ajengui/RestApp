package tn.esprit.spring.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity @Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Evaluation implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private int id;
	
	@Column(length = 3000)
	public String positive_feedback;
	
	@Column(length = 3000)
	public String negative_feedback;
	
	@Column(length = 3000)
	public String recommendation; //supervisor's section
	
	public float score_behavior;
	
	@Column(length = 3000)
	public String toDo;
	
	

	//attributs de mapping:
}
