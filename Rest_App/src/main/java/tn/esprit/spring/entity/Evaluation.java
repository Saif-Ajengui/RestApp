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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPositive_feedback() {
		return positive_feedback;
	}

	public void setPositive_feedback(String positive_feedback) {
		this.positive_feedback = positive_feedback;
	}

	public String getNegative_feedback() {
		return negative_feedback;
	}

	public void setNegative_feedback(String negative_feedback) {
		this.negative_feedback = negative_feedback;
	}

	public String getRecommendation() {
		return recommendation;
	}

	public void setRecommendation(String recommendation) {
		this.recommendation = recommendation;
	}

	public float getScore_behavior() {
		return score_behavior;
	}

	public void setScore_behavior(float score_behavior) {
		this.score_behavior = score_behavior;
	}

	public String getToDo() {
		return toDo;
	}

	public void setToDo(String toDo) {
		this.toDo = toDo;
	}
	
	

	//attributs de mapping:
}
