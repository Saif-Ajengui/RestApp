package tn.esprit.spring.entity;



import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;









@Entity
@Getter
@Setter
public class Publication  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name= "postContent")
	private String postContent;
	@Column(name= "createDate")
	private LocalDateTime createDate;
	@Column(name= "modifyDate")
	private LocalDateTime modifyDate;
	
//	@OneToMany(mappedBy="Publication", 
//			cascade={CascadeType.PERSIST, CascadeType.REMOVE},
//			fetch=FetchType.EAGER)
//	private List<Comment> commentaires;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name= "id_user")
	private User user;
	
	@ManyToOne
	@JsonIgnore
	private Departement Departement;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "publication")
	private Set<Report> Reports;
	
	
	public Publication() {
		super();
	}

	public int getIdPublication() {
		return id;
	}

	public void setIdPublication(int id) {
		this.id = id;
	}
	
	public String getPostContent() {
		return postContent;
	}

	public void setPublicationtContent(String postContent) {
		this.postContent = postContent;
	}
	
	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public LocalDateTime getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(LocalDateTime modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	/*public List<Comment> getComments() {
		return commentaires;
	}

	public void setCommentaire(List<Comment> commentaires) {
		this.commentaires = commentaires;
	}*/
	
    public Set<Report> getReports() {
		return Reports;
	}

	public void setReports(Set<Report> reports) {
		this.Reports = reports;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
	this.user = user;
	}
	public Departement getDepartement() {
		return Departement;
	}

	public void setDepartement(Departement Departement) {
	this.Departement =Departement;
	}
	
	//@Override
	//public String toString() {
	//	return "Publication [idPublication=" + id + ", postContent=" + postContent +  ", createDate="
			//	+ createDate + ", modifyDate=" + modifyDate + 
				// ", commentaire=" + commentaire + ", reports=" + reports +  "]";
	//}

	
	
	
	
	

	
}
