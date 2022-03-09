package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import tn.esprit.spring.entity.Report;
import tn.esprit.spring.service.ReportServiceImpl;

@RestController
public class ReportController {
	
	@Autowired 
	ReportServiceImpl reportServiceImpl;
	
	@PostMapping("/Report/add-Report/{id}")  
	public String addReport(@RequestBody Report Reports,@PathVariable ("id")int id) throws Exception   
	{  
		return  (reportServiceImpl.addReport( Reports, id));  
	} 
	
	@PutMapping("/Report/update-Report/{id}")  
	public String updateReports(@RequestBody Report Reports, @PathVariable("id")int id) throws Exception   
	{  
		return  reportServiceImpl.updateReport( Reports, id);  			  
	}
	@DeleteMapping("/Report/delete-Report/{id}")  
	public String deleteReport(@PathVariable("id") int id) throws Exception   
	{  
		return (reportServiceImpl.deleteReport(id));  
	} 	
	@GetMapping("/Report/get-all-Report")  
	public List<Report> getAllReports()   
	{  
		return reportServiceImpl.getAllReports();  
	}  
	@GetMapping("/Report/Report-by-Publication/{id}")
	public List<Report> getAllReportsByPublicationid(@PathVariable("id") int id) {
		return reportServiceImpl.getAllReportsByPublicationid(id);
	
}
}
