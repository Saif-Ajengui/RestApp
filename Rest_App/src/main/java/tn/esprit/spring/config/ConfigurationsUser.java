package tn.esprit.spring.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;


@Configuration
@EnableWebSecurity
public class ConfigurationsUser extends WebSecurityConfigurerAdapter  {

	
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
			/*http.authorizeRequests()  
			.antMatchers("/oauth2/**").permitAll()
			.antMatchers("examan/**").authenticated()  
            .anyRequest().permitAll()    
            .and()  
            .oauth2Login()
            .loginPage("/login")
           ;  */
		
			
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
