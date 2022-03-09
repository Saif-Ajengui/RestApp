package tn.esprit.spring.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
public class ParticipationServiceImpl implements IParticipationService{
	@Autowired
	EventServiceImp eventServiceImpl;
	@Autowired
	ParticipationRepository iParticipationRepository;
	@Autowired
	EventRepository iEventRepository;
	@Autowired
	UserRepository iUserRepository;

	
	//METIER AVANCEE :STATUS =>TEST is DONE
@Override
public String addParticipation(int iduser, int idevent) {

	Event event = iEventRepository.findById(idevent).get();
	
	
	User user = iUserRepository.findById(iduser).get();
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date date = new Date();
	int number = 0;
	Participation p = new Participation();
	ParticipationPK participationPK = new ParticipationPK();
	List<Participation>participations = (List<Participation>) iParticipationRepository.findAll();
	
	
	Date currentdate = new Date();
	if(currentdate.getTime() - event.getDate().getTime() >0)
		return "Event finished you can't participate ";
	


	
	for(int i=0 ; i<participations.size();i++) {
		if(participations.get(i).getEvent().getIdEvenement() == idevent && 
				participations.get(i).getUser().getId() == iduser)
	
			return "You are already participate !!";
		
	}
	
	if(event.getPlacesNbr() > 0) {
		
		System.out.println("/////---"+participations.size()+", "+event.getNbrTicketEarlyParticipants());
		
		
		//hethi participation bel flous l nes ely jaw lwala eraly w bensba lel paiement hathaka chkun fi stripe apres ntastouh khatro api okii testih 
		if(event.isEarlyParticipants() == true && participations.size()<event.getNbrTicketEarlyParticipants()) {

			float discPercent =(100f-event.getDiscountPercentage())/100f;  //hethi discount lykont nehki fih bekri fi add participation
			float newPrice =event.getTicketPrice() * discPercent;
		
		participationPK.setIdEvent(event.getIdEvenement());
		participationPK.setIdUser(iduser);
		event.setPlacesNbr(event.getPlacesNbr() - 1);
	    event.setParticipantsNbr(event.getParticipantsNbr() + 1);
	    System.out.println("coll amount =="+event.getCollAmount()+", newPrice="+newPrice);
		event.setCollAmount(event.getCollAmount()+ newPrice);
		user.setAccBalance(user.getAccBalance()-newPrice);
		p.setPrice(newPrice);
		p.setParticipationPK(participationPK);
		//participationPK.setNumber(number);
	//	p.setParticipationDate(new Date());
		iParticipationRepository.save(p);
		iUserRepository.save(user);
	
		}	
		//else hethi l nes lyjaw retard w mandhomch reduction f participation
		else {
			participationPK.setIdEvent(event.getIdEvenement());
			participationPK.setIdUser(iduser);
			event.setPlacesNbr(event.getPlacesNbr() - 1);
		    event.setParticipantsNbr(event.getParticipantsNbr() + 1);
		    System.out.println("coll amount =="+event.getCollAmount()+", newPrice="+event.getTicketPrice());
			event.setCollAmount(event.getCollAmount()+ event.getTicketPrice());
			user.setAccBalance(user.getAccBalance()-event.getTicketPrice());

			p.setPrice(event.getTicketPrice());
			p.setParticipationPK(participationPK);
			//participationPK.setNumber(number);
			//p.setParticipationDate(new Date());
			
			
			iParticipationRepository.save(p);

		iUserRepository.save(user);
	}
	return "Affected successfully with discount percentage";
	
}
return "Event places is full";
}

	@Override
	public List<Participation> participationsList() {
		List<Participation> list = (List<Participation>) iParticipationRepository.findAll();
		return list;
}


	@Override
	public List<Participation> myParticipations() {
		// TODO Auto-generated method stub
//		 		List<Participation>list = iParticipationRepository.myParticipations(AuthController.USERCONNECTED);

		 		return null;
	}

}
