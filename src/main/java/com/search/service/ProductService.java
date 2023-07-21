package com.search.service;

import java.util.List;

import com.search.model.ProductResponse;

import jakarta.servlet.http.HttpSession;

public interface ProductService {

	List<ProductResponse> find(String text, HttpSession session);
}
