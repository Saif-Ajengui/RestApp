package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.Partner;



public interface IPartnerService {

public Partner addPartner(Partner p , int idc) throws Exception;
	
	public String deletePartner(int id) throws Exception;
	
	public Partner updatePartner(Partner p, int id) throws Exception;
	
	public List<Partner> getAllPartner();
	
	public int CountPartnerByid(int id);



}
