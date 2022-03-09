package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import tn.esprit.spring.entity.Publication;
import tn.esprit.spring.entity.Report;



public interface IReportRepository extends CrudRepository <Report, Integer> {

	
	@Query("SELECT r FROM  Report r WHERE r.publication.id =?1")
	List<Report>findReportsByPublicationt(int id);
}
