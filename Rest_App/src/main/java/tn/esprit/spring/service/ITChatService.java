package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.TChat;
import tn.esprit.spring.response.RetourResponse;

public interface ITChatService {

	RetourResponse<TChat> sendTChat(int idReceiver, TChat Tchat) throws Exception;
	public List<TChat> receiverTChat(int idReceiver) throws Exception;
}
