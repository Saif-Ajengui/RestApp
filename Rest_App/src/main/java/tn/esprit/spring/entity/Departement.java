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
		@OneToMany(mappedBy="departement", 
				cascade={CascadeType.PERSIST, CascadeType.REMOVE},
				fetch=FetchType.EAGER)
		private List<Publication> publication;

}
