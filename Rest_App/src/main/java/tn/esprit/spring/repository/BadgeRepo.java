package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import tn.esprit.spring.entity.*;
import tn.esprit.spring.entity.Badge;

public interface BadgeRepo extends CrudRepository<Badge,Integer> {

}
