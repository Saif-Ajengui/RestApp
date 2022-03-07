package tn.esprit.spring.service;

import java.util.List;

import com.example.demo.Partner;

public interface IPartnerService {

public Partner addPartner(Partner p) throws Exception;
	
	public String deletePartner(int id) throws Exception;
	
	public Partner updatePartner(Partner p, int id) throws Exception;
	
	public List<Partner> getAllPartner();
	



}
