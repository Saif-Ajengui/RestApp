package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import tn.esprit.spring.entity.*;

public interface UserRepo extends CrudRepository<User,Integer>{

}
