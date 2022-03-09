package tn.esprit.spring.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Jackpot implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private int id;
	private float sum;
	
	public Jackpot() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Jackpot(int id, float sum) {
		super();
		this.id = id;
		this.sum = sum;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getSum() {
		return sum;
	}

	public void setSum(float sum) {
		this.sum = sum;
	}

	

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + Float.floatToIntBits(sum);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jackpot other = (Jackpot) obj;
		if (id != other.id)
			return false;
		if (Float.floatToIntBits(sum) != Float.floatToIntBits(other.sum))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Jackpot [id=" + id + ", sum=" + sum + "]";
	}
	
	


	
	
}