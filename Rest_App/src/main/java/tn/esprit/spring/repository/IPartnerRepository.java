package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Partner;

@Repository

public interface IPartnerRepository extends CrudRepository<Partner , Integer> {

	Partner findPById(int id);

}
