package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.Badge;

public interface BadgeService {

	Badge ajouterBadge(Badge badge);

	List<Badge> getBadges();

	Badge getBadgeById(int id);

	Badge updateBadge(Badge badge);

	String deleteBadge(int id);

	Badge voteBadge(int id, String vote);

}
