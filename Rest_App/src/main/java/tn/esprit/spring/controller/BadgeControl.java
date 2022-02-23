package tn.esprit.spring.controller;
import tn.esprit.spring.entity.Badge;
import tn.esprit.spring.service.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;




@RestController
@RequestMapping("/Badge")
public class BadgeControl {
	
	@Autowired
	private BadgeService badgeService;
	
	//http://localhost:8082/examen/Badge/AjoutBadge
	@PostMapping("/AjoutBadge")
	public void AjoutBadge(@RequestBody Badge badge)
	{
		badgeService.ajouterBadge(badge);
		
	}
	
	// http://localhost:8082/examen/Badge/Badges
	@GetMapping("/Badges")
	@ResponseBody
	public List<Badge> findAllBadges() {
		return badgeService.getBadges();
	}
	// http://localhost:8082/examen/Badge/{id}
	@GetMapping("/{id}")
	@ResponseBody
	public Badge findBadgeById(@PathVariable("id") int id) {
		return badgeService.getBadgeById(id);
	}
	
	// http://localhost:8082/examen/Badge/update
	@PutMapping("/update")
	@ResponseBody
	public Badge updateBadge(@RequestBody Badge badge) {
		return badgeService.updateBadge(badge);
	}

	// http://localhost:8082/examen/Badge/delete/{id}
	@DeleteMapping("/delete/{id}")
	@ResponseBody
	public String deleteBadge(@PathVariable("id") int id) {
		return badgeService.deleteBadge(id);
	}

}
