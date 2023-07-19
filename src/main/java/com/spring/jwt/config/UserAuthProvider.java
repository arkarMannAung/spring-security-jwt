package com.spring.jwt.config;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.spring.jwt.dto.UserDto;
import com.spring.jwt.services.UserService;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UserAuthProvider {
	@Value("${security.jwt.token.secret-key}")
	private String secretKey;
	
	@Autowired
	UserService userService;
	
	@PostConstruct
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}
	
	public String createToken(String login) {
		Date now = new Date();
		Date validity = new Date(now.getTime() + 3_600_000 ); // one hour
		
		return JWT.create()
				.withIssuer(login)
				.withIssuedAt(now)
				.withExpiresAt(validity)
				.sign(Algorithm.HMAC256(secretKey));
	}
	
	public Authentication validateToken(String token) {
		JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secretKey)).build();
		DecodedJWT decoded = verifier.verify(token);
		UserDto user = userService.findByLogin(decoded.getIssuer()).toUserDto();
		// roles
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
		return new UsernamePasswordAuthenticationToken(user, null,authorities);
	}
}
