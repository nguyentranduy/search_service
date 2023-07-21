package com.search.service.impl;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.search.model.LoginResponse;
import com.search.model.User;
import com.search.model.UserResponse;
import com.search.service.UserService;

import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	RestTemplate restTemplate;
	
	private static final String LOGIN_URL = "http://localhost:8092/authen/login";

	@Override
	public UserResponse doLogin(User userRequest, HttpSession session) throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		
		HttpEntity<User> requestEntity = new HttpEntity<>(userRequest, headers);
		ResponseEntity<LoginResponse> loginResponse = restTemplate
				.exchange(LOGIN_URL, HttpMethod.POST, requestEntity, LoginResponse.class);
		
		if (loginResponse.getStatusCode().equals(HttpStatus.UNAUTHORIZED)) {
			throw new Exception();
		}
		session.setAttribute("TOKEN", loginResponse.getBody().getToken());
		return loginResponse.getBody().getUser();
	}
}
