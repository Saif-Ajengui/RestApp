package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class Departement  implements Serializable {
	

		private static final long serialVersionUID = 1L;
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private Long id;
		private String name; 
		@OneToMany(mappedBy="Departement", 
				cascade={CascadeType.PERSIST, CascadeType.REMOVE},
				fetch=FetchType.EAGER)
		private List<Publication> Publication;
		
		public Departement() {
			super();
		}
		

		public Departement( String name, List<tn.esprit.spring.entity.Publication> publication) {
			super();
			
			this.name = name;
			Publication = publication;
		}


		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}
		
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
}
