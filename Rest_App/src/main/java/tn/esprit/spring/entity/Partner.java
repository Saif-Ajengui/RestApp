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
import javax.persistence.OneToOne;



import lombok.Getter;
import lombok.Setter;



@Entity
@Getter
@Setter

public class Partner implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String address;
	
	private String email;
	
	private int num_tel;
	
	private String imgLogo;
	
	
	
	//Att de mapping:
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public int getNum_tel() {
		return num_tel;
	}


	public void setNum_tel(int num_tel) {
		this.num_tel = num_tel;
	}


	public String getImgLogo() {
		return imgLogo;
	}


	public void setImgLogo(String imgLogo) {
		this.imgLogo = imgLogo;
	}


	public List<Offre> getOffre() {
		return offres;
	}


	public void setOffre(List<Offre> offre) {
		this.offres = offre;
	}


	public Contrat getContrat() {
		return contrat;
	}


	public void setContrat(Contrat contrat) {
		this.contrat = contrat;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@OneToMany(mappedBy="partner", 
			cascade={CascadeType.PERSIST, CascadeType.REMOVE},
			fetch=FetchType.EAGER)
   private List<Offre> offres;
	
	
	@OneToOne
	private Contrat contrat;
	

		
	
	
	
}
