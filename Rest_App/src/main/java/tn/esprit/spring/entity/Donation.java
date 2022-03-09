package tn.esprit.spring.entity;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;


	@Entity
	public class Donation implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		@Id
		@GeneratedValue( strategy = GenerationType.IDENTITY )
		private int id;
		@ManyToOne
		@JsonIgnore
		private Event event;
		@ManyToOne
		@JsonIgnore
		private User user;
		private String contributionDate;
		private float amount;
		
		public Donation() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Donation(int id, Event event, User user, String contributionDate, float amount) {
			super();
			this.id = id;
			this.event = event;
			this.user = user;
			this.contributionDate = contributionDate;
			this.amount = amount;
		}
		
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public Event getEvent() {
			return event;
		}

		public void setEvent(Event event) {
			this.event = event;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public String getContributionDate() {
			return contributionDate;
		}

		public void setContributionDate(String contributionDate) {
			this.contributionDate = contributionDate;
		}

		public float getAmount() {
			return amount;
		}

		public void setAmount(float amount) {
			this.amount = amount;
		}

		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + Float.floatToIntBits(amount);
			result = prime * result + ((contributionDate == null) ? 0 : contributionDate.hashCode());
			result = prime * result + ((event == null) ? 0 : event.hashCode());
			result = prime * result + id;
			result = prime * result + ((user == null) ? 0 : user.hashCode());
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
			Donation other = (Donation) obj;
			if (Float.floatToIntBits(amount) != Float.floatToIntBits(other.amount))
				return false;
			if (contributionDate == null) {
				if (other.contributionDate != null)
					return false;
			} else if (!contributionDate.equals(other.contributionDate))
				return false;
			if (event == null) {
				if (other.event != null)
					return false;
			} else if (!event.equals(other.event))
				return false;
			if (id != other.id)
				return false;
			if (user == null) {
				if (other.user != null)
					return false;
			} else if (!user.equals(other.user))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Donation [id=" + id + ", event=" + event + ", user=" + user + ", contributionDate="
					+ contributionDate + ", amount=" + amount + "]";
		}
			
	}