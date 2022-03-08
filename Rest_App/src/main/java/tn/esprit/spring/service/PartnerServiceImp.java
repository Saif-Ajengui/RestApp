package tn.esprit.spring.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Contrat;
import tn.esprit.spring.entity.Partner;
import tn.esprit.spring.repository.IContratRepository;
import tn.esprit.spring.repository.IPartnerRepository;


@Service

public class PartnerServiceImp implements IPartnerService{
	
	@Autowired 
	
	IPartnerRepository PartnerRepository;
	
	@Autowired
	IContratRepository ContratRepository;
	
	@Override
	public Partner addPartner(Partner p , int idc) throws Exception {
		Contrat c = ContratRepository.findById(idc).get();
		p.setContrat(c);
		c.setPartner(p);
		PartnerRepository.save(p);
		return p;
	}

	@Override
	public String deletePartner(int id) throws Exception {
		PartnerRepository.deleteById(id);
		return "DELETED";
	}

	@Override
	public Partner updatePartner(Partner p, int id) throws Exception {
		Partner u = PartnerRepository.findPById(id);
			u.setName(p.getName());
			u.setAddress(p.getAddress());
			u.setEmail(p.getEmail());
			u.setNum_tel(p.getNum_tel());
			
			return PartnerRepository.save(u);
		}
		
	

	@Override
	public List<Partner> getAllPartner() {
		return  (List<Partner>) PartnerRepository.findAll();
	}

	@Override
	public int CountPartnerByid(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
