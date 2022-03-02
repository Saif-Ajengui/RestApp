package tn.esprit.spring.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.TChat;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.TchatRepository;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.response.RetourResponse;

@Service
public class TChatService implements ITChatService{

	
	@Autowired
	UserRepository userRepository;
	@Autowired
	TchatRepository tchatRepository;
	
	@Override
	public RetourResponse<TChat> sendTChat(int idReceiver, TChat Tchat) throws Exception {
		RetourResponse<TChat> response = new RetourResponse<>();
		User sender = userRepository.findById(1).get();
		User receiver = userRepository.findById(idReceiver).get();
		Tchat.setSender(sender);
		Tchat.setReceiver(receiver);
		Tchat.setDate(new Date(System.currentTimeMillis()));
		Tchat.setTime(new Date(System.currentTimeMillis()));
		tchatRepository.save(Tchat);
		response.setStringValue("message sent successfully!!");
		response.getObjectValue().add(Tchat);
		
		return response;
	}
	
	@Override
	public List<TChat> receiverTChat(int idReceiver) throws Exception {
		User sender = userRepository.findById(1).get();
		User receiver = userRepository.findById(idReceiver).get();
		//User receiver = getCurrentUser();
		List<TChat> tchat = tchatRepository.getAllOrderByTime();
		List<TChat> tchatShow = new ArrayList<>();
		for (TChat message : tchat) {
			if (message.getSender().getId() == sender.getId()
					& message.getReceiver().getId() == receiver.getId()) {
				tchatShow.add(message);
			}
			if (message.getSender().getId() == receiver.getId()
					& message.getReceiver().getId() == sender.getId()) {
				tchatShow.add(message);
			}
		}
		return tchatShow;
	}

}
