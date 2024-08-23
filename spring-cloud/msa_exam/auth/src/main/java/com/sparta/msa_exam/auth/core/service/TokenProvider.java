package com.sparta.msa_exam.auth.core.service;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class TokenProvider {

	@Value("${spring.application.name}")
	private String issuer;

	@Value("${service.jwt.access-expiration}")
	private Long accessExpiration;

	private final SecretKey secretKey;

	public TokenProvider(@Value("${service.jwt.secret-key}") String secretKey) {
		this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey));
	}

	public String createAccessToken(String userId, String role) {
		return Jwts.builder()
			.claim("user_id", userId)
			.claim("role", role)
			.issuer(issuer)
			.issuedAt(new Date(System.currentTimeMillis()))
			.expiration(new Date(System.currentTimeMillis() + accessExpiration))
			.signWith(secretKey, io.jsonwebtoken.SignatureAlgorithm.HS512)
			.compact();
	}
}
