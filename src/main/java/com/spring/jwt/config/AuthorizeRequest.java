package com.spring.jwt.config;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;

public class AuthorizeRequest {
	static void configureAuthorizeRequests(
			AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry request) {
		request.requestMatchers(HttpMethod.POST, "/api/v1/login", "/api/v1/register").permitAll()
				.requestMatchers("/api/v1/create-user").hasAnyAuthority("1")
				.requestMatchers(HttpMethod.GET, "/api/v1/public").permitAll();
	}
}
