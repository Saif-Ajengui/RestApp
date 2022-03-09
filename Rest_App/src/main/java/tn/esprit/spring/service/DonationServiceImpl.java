package tn.esprit.spring.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Event;
import tn.esprit.spring.entity.Jackpot;

import tn.esprit.spring.entity.User;
import tn.esprit.spring.entity.Donation;
import tn.esprit.spring.repository.EventRepository;
import tn.esprit.spring.repository.IDonationRepository;
import tn.esprit.spring.repository.IJackPotRepository;
import tn.esprit.spring.repository.UserRepository;



//Add Donation to a jackpot an event
@Service
public class DonationServiceImpl implements IDonationService  {

	@Autowired
	EventRepository iEventRepository;
	
	@Autowired
	IJackPotRepository iJackpotRepository;
	
	@Autowired
	IDonationRepository iDonationRepository;
	
	@Autowired
	UserRepository iUserRepository;
	
	@Autowired
	IPaymentService iPay;
	//Donation lel event w kol event endo jackpot w kol jackpot fiha somme donation
	@Override
	public String Donation(int eid,int uid, float amount) {
		float totale=0;
		float newCollAmount=0;
		tn.esprit.spring.entity.Donation donation = new tn.esprit.spring.entity.Donation();
		Event ev = iEventRepository.findById(eid).get();
		User user = iUserRepository.findById(uid).get();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		//attribut static  dans l'user 
		Jackpot jackpot = new Jackpot();
		System.out.println(ev.getJackpot());
		
		
		if(date.getTime() - ev.getDate().getTime() >0)
			return "Event finished you can't donate  ";
	
		if(user.getAccBalance() >=amount) {
			user.setAccBalance(user.getAccBalance() - amount);
			Jackpot j = iJackpotRepository.findJackpotEvent(ev.getIdEvenement());
			j.setSum(j.getSum() + amount);
			ev.setCollAmount(ev.getCollAmount() + amount);
			donation.setAmount(amount);
			donation.setContributionDate(dateFormat.format(date));
			donation.setEvent(ev);
			donation.setUser(user);
			iUserRepository.save(user);
			iDonationRepository.save(donation);
			iJackpotRepository.save(j);
			iEventRepository.save(ev);
			/*totale = user.getAccBalance() - amount;
			jackpot = iEventRepository.findJackpot(ev.getJackpot());
			System.out.println("sum="+jackpot.getTotal());
			donation.setAmount(amount);
			donation.setDate(date);
			donation.setEvent(ev);
			donation.setUser(user);
			jackpot.setTotal(jackpot.getTotal()+amount);
			iJackpotRepository.save(jackpot);
			iEventRepository.save(ev);
            iDonationRepository.save(donation);*/

			//PAIEMENT STRIPE
			iPay.chargeCustomer((int)amount);
			
			return "Donation saved successfully!!";
		}
		return "Balance(Salaire) amount below than amount of donation we are sorry ";
	}

	@Override
	public List<tn.esprit.spring.entity.Donation> getDonationEvention(Event event) {
		List<tn.esprit.spring.entity.Donation> list = iDonationRepository.DonationOfEvent(event);
		return list;
	}

	@Override
	public List<Donation> getHistoryDonation() {
		return null;
		/*List<Donation> list = iDonationRepository.DonationOfUser(UserController.USERCONNECTED);
		return list;*/
	}


}