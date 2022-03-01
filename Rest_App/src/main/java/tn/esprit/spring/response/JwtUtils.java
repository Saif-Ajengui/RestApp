package tn.esprit.spring.response;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {
	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
	public static final String SECRET ="Saif"; //clé secret
	public static final long EXPIRATION_TIME= 432_000_000; //5 jours
	
	public String generateJwtToken(Authentication authentication) {
		
		
	AuthDetailsUser userPrincipal = (AuthDetailsUser) authentication.getPrincipal();

		return Jwts.builder()
				.setSubject((userPrincipal.getUsername()))
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime()+EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET)
				.compact();
				

	}


}

