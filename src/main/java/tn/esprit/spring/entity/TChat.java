package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "TChat")
public class TChat implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int idTChat;

	@Column(name = "textMessage")
	private String textMessage;

	@Column(name = "pic", nullable = true)
	@Lob
	private byte[] pic;

	@Temporal(TemporalType.DATE)
	@Column(name = "dateSends")
	private Date date;

	@Temporal(TemporalType.TIME)
	@Column(name = "time")
	private Date time;

	@ManyToOne
	@JoinColumn(name = "id_sender")
	private User sender;

	@ManyToOne
	@JoinColumn(name = "id_receiver")
	private User receiver;

	

}
