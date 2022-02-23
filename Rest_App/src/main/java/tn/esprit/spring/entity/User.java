package tn.esprit.spring.entity;

import java.io.Serializable;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
	@Column(name = "pic")
	private String pic;
	@Column(name = "email")
	private String email;
	@Column(name = "pwd")
	private String pwd;
	@Column(name = "approved")
	private boolean approved;
	
	@Column(name = "type")
	@Enumerated(EnumType.STRING)
	private Role type;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
	private Set<Notification> notif;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "sender", fetch = FetchType.EAGER)
	private Set<TChat> TChatS;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "receiver", fetch = FetchType.EAGER)
	private Set<TChat> TChatR;

	
	
	
}
