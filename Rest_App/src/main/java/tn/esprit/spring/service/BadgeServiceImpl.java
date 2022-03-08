package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entity.Badge;
import tn.esprit.spring.repository.BadgeRepo;

@Slf4j
@Service
public class BadgeServiceImpl implements BadgeService{
	
	@Autowired
	private BadgeRepo badgeRepo;

	@Override
	public Badge ajouterBadge(Badge badge) {
		// TODO Auto-generated method stub
		return badgeRepo.save(badge);
		
	}

	@Override
	public List<Badge> getBadges() {
		// TODO Auto-generated method stub
		return (List<Badge>) badgeRepo.findAll();
	}

	@Override
	public Badge getBadgeById(int id) {
		// TODO Auto-generated method stub
		return badgeRepo.findById(id).get();
	}

	@Override
	public Badge updateBadge(Badge badge) {
		// TODO Auto-generated method stub
		Badge b = badgeRepo.findById(badge.getId()).orElse(null);
		b.setImg(badge.getImg());
		b.setNbVoteABS(badge.getNbVoteABS());
		b.setNbVoteNo(badge.getNbVoteNo());
		b.setNbVoteYes(badge.getNbVoteYes());
		
		return badgeRepo.save(b);
	}

	@Override
	public String deleteBadge(int id) {
		// TODO Auto-generated method stub
		badgeRepo.deleteById(id);
		return "badge "+id+" is deleted";
	}

	@Override
	public Badge voteBadge(int id, String vote) {
		// TODO Auto-generated method stub
		Badge b = badgeRepo.findById(id).orElse(null);
		if(vote.compareToIgnoreCase("yes") == 0)
		{
			b.nbVoteYes++;
			badgeRepo.save(b);
			return b;
		}
		else if(vote.compareToIgnoreCase("no") == 0)
		{
			b.nbVoteNo++;
			badgeRepo.save(b);
			return b;
		}
		else if(vote.compareToIgnoreCase("abs") == 0)
		{
			b.nbVoteABS++;
			badgeRepo.save(b);
			return b;
		}
		else
		{
			return null;
		}
		
	}

}
