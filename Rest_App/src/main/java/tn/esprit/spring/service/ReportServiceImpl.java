package tn.esprit.spring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Departement;
import tn.esprit.spring.entity.Publication;
import tn.esprit.spring.entity.Report;
import tn.esprit.spring.repository.IPublicationRepository;
import tn.esprit.spring.repository.IReportRepository;
@Service
public class ReportServiceImpl  implements IReportService {

	
	@Autowired 
	IReportRepository ReportRepository;
	
	@Autowired 
	IPublicationRepository PublicationRepository;

	@Override
	public String addReport(Report r , int idp) throws Exception {
		Publication pup =PublicationRepository.findById(idp).get();
		r.setPublication(pup);
			
		
			ReportRepository.save(r);
			return ("Report ajouter"); 
	}

	@Override
	public String deleteReport(int id) throws Exception {
		ReportRepository.deleteById((int) id);
		  return "Report supprimer";
	}

	@Override
	public String updateReport(Report r, int id) throws Exception {
		ReportRepository.deleteById((int) id);
		  return "Report supprimer";
	}

	@Override
	public List<Report> getAllReports() {
		
			List<Report>Reports = new ArrayList<Report>();
			ReportRepository.findAll().forEach(e ->Reports.add(e));
			return Reports;
	}

	@Override
	public List<Report> getAllReportsByPublicationid( int id) {
		return	ReportRepository.findReportsByPublicationt(id);
		
	}

	
	
}
