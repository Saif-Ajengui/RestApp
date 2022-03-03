package tn.esprit.spring.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entity.Task;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.TaskRepo;
import tn.esprit.spring.repository.UserRepo;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private TaskRepo taskRepo;

	@Override
	public List<Integer> getUserByDept(String deptName) {
		// TODO Auto-generated method stub
		return userRepo.findByDept(deptName);

	}

	@Override
	@Transactional
	public void assignTaskToUser(int idTask, int idUser) {
		// TODO Auto-generated method stub
		Task taskManagEntity = taskRepo.findById(idTask).get();
		User userManagEntity = userRepo.findById(idUser).get();

		if (taskManagEntity.getUsers() == null) {
			List<User> users = null;
			users.add(userManagEntity);
			taskManagEntity.setUsers(users);
		} else {
			taskManagEntity.getUsers().add(userManagEntity);
		}
	}

	@Transactional
	@Override
	public void unassignTaskToUser(int idTask, int idUser) {
		// TODO Auto-generated method stub
		Task task = taskRepo.findById(idTask).get();

		int userNb = task.getUsers().size();
		for (int index = 0; index < userNb; index++) {
			if (task.getUsers().get(index).getId() == idUser) {
				task.getUsers().remove(index);
				break;// a revoir
			}
		}

	}

}
