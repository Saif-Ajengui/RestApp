package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entity.Badge;
import tn.esprit.spring.entity.Deposit;
import tn.esprit.spring.entity.Task;
import tn.esprit.spring.repository.TaskRepo;
import tn.esprit.spring.repository.DepositRepo;

@Slf4j
@Service
public class DepositServiceImpl implements DepositService{
	
	@Autowired
	private DepositRepo depositRepo;
	
	@Autowired
	private TaskRepo taskRepo;

	@Override
	public Deposit assignDepositToTask(int idDepo, int idTask) {
		// TODO Auto-generated method stub
		Task t = taskRepo.findById(idTask).get();
		Deposit d = depositRepo.findById(idTask).get();
		d.setTask(t);
		return depositRepo.save(d);
		
	}

	@Override
	public Deposit AddDeposit(Deposit bd) {
		// TODO Auto-generated method stub
		return depositRepo.save(bd);
	}
	

}
