package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.Report;



public interface IReportService {
	
public String addReport(Report r , int idp) throws Exception;
	
	public String deleteReport(int id) throws Exception;
	public String updateReport(Report r, int id) throws Exception;
	public List<Report> getAllReports();
	public List<Report> getAllReportsByPublicationid(int id );


}
