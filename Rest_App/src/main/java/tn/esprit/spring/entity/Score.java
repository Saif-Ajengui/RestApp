package tn.esprit.spring.entity;

import java.io.Serializable;

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
public class Score implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private int id;

	//productivity : ddl task respected 
	//efficiency depends: score task ....
	
	private float avgscoreTask;
	private float avgscoreTask_Monthly;
	
	private float scoreEfficiency;
	
	private float scoreProductivity;
	
	private float percentDDLRespected;
	
	private float percentreactivity; //depends on activities / comments / feedback.... => team integration

	
	

	//attributs de mapping:
}
