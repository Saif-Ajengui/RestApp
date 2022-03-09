package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;




@Entity
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int idUser;
	@Column(name = "firstName")
	private String firstName;
	@Column(name = "lastName")
	private String lastName;
	@Column(name = "Role")
	private String Role;
	
	//@OneToMany(mappedBy="User", 
		//	cascade={CascadeType.PERSIST, CascadeType.REMOVE},
			//fetch=FetchType.EAGER)
	//private List<Publication> publication;
	
	
	
	//@JsonIgnore
	//@OneToMany(cascade = CascadeType.ALL, mappedBy = "pub")
//	private Set<Publication> publications;
	//@JsonIgnore
	//@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
//	private Set<Commentaire> Commentaires;
	
	@OneToMany(mappedBy="User", 
			cascade={CascadeType.PERSIST, CascadeType.REMOVE},
			fetch=FetchType.EAGER)
	private List<Comment> commentaires;
	
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Publication> Publications;
	
	
	
	
	
	
	
	
	
	public User() {
		super();
		
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getRole() {
		return Role;
	}
	
	public void setRole(String Role) {
		this.Role = Role;
	}

}
