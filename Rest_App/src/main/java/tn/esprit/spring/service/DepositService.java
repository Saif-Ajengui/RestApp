package tn.esprit.spring.service;

import tn.esprit.spring.entity.Deposit;

public interface DepositService {

	Deposit assignDepositToTask(int idDepo, int idTask);

	Deposit AddDeposit(Deposit bd);

}
