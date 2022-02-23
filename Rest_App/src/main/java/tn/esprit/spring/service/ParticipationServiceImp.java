package tn.esprit.spring.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Event;
import tn.esprit.spring.entity.Participation;
import tn.esprit.spring.entity.ParticipationPK;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.EventRepository;
import tn.esprit.spring.repository.ParticipationRepository;
import tn.esprit.spring.repository.UserRepository;

@Service
public class ParticipationServiceImp implements IParticipationService {

	@Autowired
	private EventRepository iEventRepository;

	@Autowired
	private UserRepository iUserRepository;
	@Override
	public String addParticipation(int iduser, int idevent) {
			Event event = iEventRepository.findById(idevent).get();
		
		
		User user = iUserRepository.findById(iduser).get();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		int number = 0;
		Participation p = new Participation();
		ParticipationPK participationPK = new ParticipationPK();
		participationPK.setIdEvent(event.getIdEvenement());
		participationPK.setIdUser(iduser);
		p.setParticipationDate(dateFormat.format(date));	

		p.setPrice(event.getTicketPrice());
		p.setParticipationPK(participationPK);
		return "Particpation affected successfully!!!";

	}
	



		
}
