package tn.esprit.spring.service;

import org.json.JSONException;
import org.springframework.http.ResponseEntity;

public interface IRatingService {

	public String recommendedEvents(int  userId) throws JSONException ;
	public  ResponseEntity<?>evaluateEvent(int idUser ,int idProduct , int rate);
	String updateRating(int iduser, int idproduct, int idRate, int rate);
	int countRatingUserByEvent(int idp);
	
	
}
