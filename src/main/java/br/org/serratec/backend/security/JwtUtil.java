package br.org.serratec.backend.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
	@Value("${auth.jwt-secret}")
	private String jwtSecret;
	@Value("${auth.jwt-expiration-miliseg}")
	private Long jwtExpirationMiliseg;

	public String genereteToken(String nome) {
		System.out.println("GERAR TOKEN");
		return Jwts.builder().setSubject(nome)
				.setExpiration(new Date(System.currentTimeMillis() 
				+ this.jwtExpirationMiliseg))
				.signWith(SignatureAlgorithm.HS512,
				this.jwtSecret.getBytes()).compact();
	}
	
	public boolean isValidToken(String token) {
		System.out.println("É INVALIDO TOKEN");
		Claims claims = getClaims(token);
		if (claims != null) {
			String username = claims.getSubject();
			Date expirationDate = claims.getExpiration();
			Date now = new Date(System.currentTimeMillis());
			if (username != null 
					&& expirationDate != null && now.before(expirationDate)) {
				return true;
			}
		}
		return false;
	}

	public String getUsername(String token) {
		System.out.println("GET USERNAME");
		Claims claims = getClaims(token);
		if (claims != null) {
			return claims.getSubject();
		}
		return null;
	}
	
	private Claims getClaims(String token) {
		System.out.println("GERAR CLAIMS");
		try {
			return Jwts.parser().setSigningKey(jwtSecret.getBytes()).parseClaimsJws(token).getBody();
		}
		catch (Exception e) {
			return null;
		}
	}
}


