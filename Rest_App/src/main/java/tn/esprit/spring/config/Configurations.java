package tn.esprit.spring.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



@Configuration
public class Configurations extends WebSecurityConfigurerAdapter  {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
	}
	
	
	 @Bean
	 public JavaMailSender javaMailSender() { 
	    	JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    	mailSender.setHost("smtp.gmail.com");
	    	mailSender.setPort(587);
	    	mailSender.setUsername("restapp2022@gmail.com");
	    	mailSender.setPassword("123456Saif");
	    	Properties props = mailSender.getJavaMailProperties();
	    	props.put("mail.transport.protocol", "smtp");
	    	props.put("mail.smtp.auth", "true");
	    	props.put("mail.smtp.starttls.enable", "true");
	    	props.put("mail.debug", "true");
	    	return mailSender;
	    }
	 
}
