package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.Donation;
import tn.esprit.spring.entity.Event;



public interface IDonationService {

	public String Donation(int eid,int uid, float amount);
	public List<Donation> getDonationEvention(Event event) ;
	public List<Donation> getHistoryDonation();
}