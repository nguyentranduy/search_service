package com.search.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.search.model.ProductResponse;
import com.search.service.ProductService;

import jakarta.servlet.http.HttpSession;

@Service
public class ProductServiceImpl implements ProductService {
	
	private static final String SEARCH_URL = "http://localhost:8091/product/";
	
	@Autowired 
	RestTemplate restTemplate;

	@Override
	public List<ProductResponse> find(String text, HttpSession session) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.set("Authorization", "Bearer " + session.getAttribute("TOKEN"));
		
		HttpEntity<String> requestEntity = new HttpEntity<>(text, headers);
		ResponseEntity<ProductResponse[]> responseEntity = restTemplate
				.exchange(SEARCH_URL, HttpMethod.POST, requestEntity, ProductResponse[].class);

		List<ProductResponse> productResponseList = new ArrayList<>();
		if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
			productResponseList = List.of(responseEntity.getBody());	
		}
		
		return productResponseList;
	}
}
