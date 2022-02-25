package tn.esprit.spring.service;

import org.springframework.mail.SimpleMailMessage;

public interface ISendEmail {
	public void sendEmail(SimpleMailMessage email);
}
