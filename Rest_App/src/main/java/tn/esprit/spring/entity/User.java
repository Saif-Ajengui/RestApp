package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "User")
public class User implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "lname")
	private String lname;
	@Column(name = "fname")
	private String fname;
	@Column(name = "birthdate")
	private String birthdate;
	@Column(name = "pic", nullable = true)
	@Lob
	private byte[] pic;
	@Column(name = "email")
	private String email;
	@Column(name = "pwd")
	private String pwd;
	@Column(name = "approved")
	private boolean approved;
	
	@Column(name = "type")
	@Enumerated(EnumType.STRING) 
	private Role type;
	
	@Column(name = "resettoken")
	private String resettoken;
	
	private String deptName;

	// attributs de mapping:
	@OneToOne
	private Badge badge;
	
	//@JsonIgnore
	@ManyToMany(mappedBy="users",fetch=FetchType.EAGER)
	private List<Task> task;
	

	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
	private Set<Notification> notif;
	
	
	@OneToMany(cascade= CascadeType.ALL)
	private List<Evaluation> evaluation;
	


	@OneToMany(cascade = CascadeType.ALL, mappedBy="U_Articles")
	private Set<Article> Articles;
		
	@ManyToMany(cascade = CascadeType.ALL) 
	private Set<Question> U_Question;
	

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "sender", fetch = FetchType.EAGER)
	private Set<TChat> TChatS;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "receiver", fetch = FetchType.EAGER)
	private Set<TChat> TChatR;
	
	
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="user")
	private List<Rating>evaluations;
	
	private float accBalance; 

	
	
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "User")
	private Set<Comment> commentaires;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Publication> Publications;
	


	

	public String getDeptName() {
		return deptName;
	}


	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}


	public Badge getBadge() {
		return badge;
	}


	public void setBadge(Badge badge) {
		this.badge = badge;
	}


	public List<Task> getTask() {
		return task;
	}


	public void setTask(List<Task> task) {
		this.task = task;
	}


	public List<Evaluation> getEvaluation() {
		return evaluation;
	}


	public void setEvaluation(List<Evaluation> evaluation) {
		this.evaluation = evaluation;
	}


	

	public Set<TChat> getTChatS() {
		return TChatS;
	}


	public void setTChatS(Set<TChat> tChatS) {
		TChatS = tChatS;
	}


	public Set<TChat> getTChatR() {
		return TChatR;
	}


	public void setTChatR(Set<TChat> tChatR) {
		TChatR = tChatR;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	
	public byte[] getPic() {
		return pic;
	}


	public void setPic(byte[] pic) {
		this.pic = pic;
	}


	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public boolean isApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	public Role getType() {
		return type;
	}
	public void setType(Role type) {
		this.type = type;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public Integer getId() {
		return id;
	}
	public String getResettoken() {
		return resettoken;
	}
	public String setResettoken(String resettoken) {
		this.resettoken = resettoken;
		return resettoken;
	}


	public Set<Notification> getNotif() {
		return notif;
	}


	public void setNotif(Set<Notification> notif) {
		this.notif = notif;
	}


	public List<Rating> getEvaluations() {
		return evaluations;
	}


	public void setEvaluations(List<Rating> evaluations) {
		this.evaluations = evaluations;
	}


	public float getAccBalance() {
		return accBalance;
	}


	public void setAccBalance(float accBalance) {
		this.accBalance = accBalance;
	}


	public User(Integer id, String fname, String deptName) {
		super();
		this.id = id;
		this.fname = fname;
		this.deptName = deptName;
	}


	public User(Integer id, String fname, String deptName, Badge badge, List<Task> task, List<Evaluation> evaluation) {
		super();
		this.id = id;
		this.fname = fname;
		this.deptName = deptName;
		this.badge = badge;
		this.task = task;
		this.evaluation = evaluation;
	}


	@Override
	public String toString() {
		return "User [lname=" + lname + ", fname=" + fname + ", birthdate=" + birthdate + ", pic="
				 + ", email=" + email + ", pwd=" + pwd + ", type=" + type + "]";
	}
	
	
	
	
	
	
}
