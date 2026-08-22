package com.jdc.spring.demo.utils.security;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.jdc.spring.demo.utils.exceptions.TokenAccessExpiredException;
import com.jdc.spring.demo.utils.exceptions.TokenInvalidateException;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

public class JwtTokenService {
	
	public enum Type {
		Access, Refresh
	}
	
	private static final String ROLE = "rol";
	private static final String NAME = "nam";
	private static final String TYPE = "type";
	
	private SecretKey secretKey = Jwts.SIG.HS512.key().build();

	@Value("${app.jwt.token.issuer}")
	private String issuer;
	@Value("${app.jwt.token.access-life}")
	private int accessLife;
	@Value("${app.jwt.token.refresh-life}")
	private int refreshLife;	

	public String generateAccess(Authentication authentication) {
		return generate(authentication, Type.Access);
	}

	public String generateRefresh(Authentication authentication) {
		return generate(authentication, Type.Refresh);
	}

	public Authentication parseAccessToken(String token) {
		try {
			return parse(token, Type.Access);
		} catch (ExpiredJwtException e) {
			throw new TokenAccessExpiredException(e);
		} catch (JwtException e) {
			throw new TokenInvalidateException("Invalid access information. Please login again.", e);
		}
	}

	public Authentication parseRefreshToken(String token) {
		try {
			return parse(token, Type.Refresh);
		} catch (ExpiredJwtException e) {
			throw new TokenInvalidateException("Your access is expired. Please login again.", e);
		} catch (JwtException e) {
			throw new TokenInvalidateException("Invalid access information. Please login again.", e);
		}
	}

	private String generate(Authentication authentication, Type type) {
		
		var appUser = (ApplicationUser)authentication.getPrincipal();
		
		var issueAt = new Date();
		
		return Jwts.builder()
			.subject(authentication.getName()) 					// Login ID
			.claim(NAME, appUser.getName())						// Account Name
			.claim(ROLE, authentication.getAuthorities()		// Authorities
					.stream().map(a -> a.getAuthority())
					.collect(Collectors.joining(",")))
			.claim(TYPE, type)
			.signWith(secretKey)
			.issuer(issuer)
			.issuedAt(issueAt)
			.expiration(expiration(type, issueAt))
			.compact();
		
	}

	private Authentication parse(String token, Type type) {
		
		var payload = Jwts.parser()
			.requireIssuer(issuer)
			.verifyWith(secretKey)
			.build()
			.parseSignedClaims(token)
			.getPayload();
		
		var typeValue = payload.get(TYPE, String.class);
		
		if(!type.name().equals(typeValue)) {
			// Throw Exception
		}
		
		var username = payload.getSubject();
		var authoritiesArray = payload.get(ROLE, String.class).split(",");
		
		var userDetails = User.withUsername(username).authorities(authoritiesArray).build();
		
		var accountName = payload.get(NAME, String.class);
		var appuser = new ApplicationUser((User)userDetails, accountName);
		
		var authorityList = Arrays.stream(authoritiesArray).map(a -> new SimpleGrantedAuthority(a)).toList();
		return UsernamePasswordAuthenticationToken.authenticated(appuser, null, authorityList);
	}

	private Date expiration(Type type, Date issueAt) {
		
		var calendar = Calendar.getInstance();
		calendar.setTime(issueAt);
		
		if(type == Type.Access) {
			calendar.add(Calendar.MINUTE, accessLife);
		} else if (type == Type.Refresh) {
			calendar.add(Calendar.MINUTE, refreshLife);
		}
		
		return calendar.getTime();
	}

	
}
